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
package org.kie.pmml.benchmark.trusty.drools.linearregression;

import org.kie.api.pmml.PMML4Result;
import org.kie.api.pmml.PMMLRequestData;
import org.kie.pmml.api.runtime.PMMLContext;
import org.kie.pmml.api.runtime.PMMLRuntime;
import org.kie.pmml.benchmark.common.linearregression.SimpleAbstractLinearRegressionBenchmark;
import org.kie.pmml.evaluator.core.PMMLContextImpl;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.kie.pmml.benchmark.trusty.drools.linearregression.LinearRegressionBuilder.getLinearRegressionPMMLRuntime;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@Warmup(iterations = 12, time = 20)
@Measurement(iterations = 5, time = 20)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2)
public class SimpleLinearRegressionBenchmark extends SimpleAbstractLinearRegressionBenchmark {

    private PMMLContext pmmlContext;

    @State(Scope.Benchmark)
    public static class MyState {
        public PMMLRuntime pmmlRuntime;

        @Setup(Level.Trial)
        public void initialize() {
            pmmlRuntime = getLinearRegressionPMMLRuntime();
        }

        @TearDown(Level.Trial)
        public void shutdown() {
            // Nothing to do
        }
    }

    @Setup
    public void setupModel() {
        PMMLRequestData pmmlRequestData = new PMMLRequestData("123", MODEL_NAME);
        INPUT_DATA.forEach(pmmlRequestData::addRequestParam);
        pmmlContext = new PMMLContextImpl(pmmlRequestData);
    }

    @Benchmark
    public PMML4Result evaluate(MyState state) {
        return state.pmmlRuntime.evaluate(MODEL_NAME, pmmlContext);
    }

}
