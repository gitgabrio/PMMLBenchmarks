/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.pmml.benchmark.jpmml;

import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.ModelEvaluator;
import org.kie.pmml.benchmark.common.SimpleAbstractBenchmark;
import org.openjdk.jmh.annotations.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.kie.pmml.benchmark.jpmml.Builder.getModelEvaluator;

@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
@Warmup(iterations = 2)
@Measurement(iterations = 5, time = 30)
@OutputTimeUnit(TimeUnit.SECONDS)
//@Fork(value = 5)
public class SimpleBenchmark extends SimpleAbstractBenchmark {

    private Map<FieldName, FieldValue> arguments;

    @State(Scope.Benchmark)
    public static class MyState {
        public ModelEvaluator<?> evaluator;

        @Setup(Level.Trial)
        public void initialize() {
            evaluator = getModelEvaluator();
        }

        @TearDown(Level.Trial)
        public void shutdown() {
            // Nothing to do
        }
    }

    @Setup
    public void setupModel(MyState state) {
        System.out.println("setup arguments...");
        arguments = getArguments(state.evaluator.getInputFields());
        System.out.println("... setup complete!");
    }

    @Benchmark
    public Map<FieldName, ?> evaluate(MyState state) {
        return state.evaluator.evaluate(arguments);
    }

    private Map<FieldName, FieldValue> getArguments(List<? extends InputField> inputFields) {
        Map<FieldName, FieldValue> toReturn = new LinkedHashMap<>();
        // Mapping the record field-by-field from data source schema to PMML schema
        for (InputField inputField : inputFields) {
            FieldName inputName = inputField.getName();
            Object rawValue = INPUT_DATA.get(inputName.getValue());
            // Transforming an arbitrary user-supplied value to a known-good PMML value
            FieldValue inputValue = inputField.prepare(rawValue);
            toReturn.put(inputName, inputValue);
        }
        return toReturn;
    }


}
