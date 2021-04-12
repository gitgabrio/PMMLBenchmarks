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
package org.kie.pmml.benchmark.jpmml.randomforest;

import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.ModelEvaluator;
import org.kie.pmml.benchmark.common.randomforest.SimpleAbstractRandomForestBenchmark;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.kie.pmml.benchmark.jpmml.randomforest.RandomForestBuilder.getRandomForestModelEvaluator;

public class SimpleRandomForestMain extends SimpleAbstractRandomForestBenchmark {

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        long startTime = System.currentTimeMillis();
        ModelEvaluator<?> evaluator = getRandomForestModelEvaluator();
        long intermediateTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            Map<FieldName, FieldValue> arguments = setupModel(evaluator);
            evaluate(evaluator, arguments, counter);
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("ModelEvaluator instantiation took " + (intermediateTime - startTime) + " ms");
        System.out.println("Loop execution took " + (stopTime - intermediateTime) + " ms");
    }

    private static Map<FieldName, FieldValue> setupModel(ModelEvaluator<?> evaluator) {
        Map<FieldName, FieldValue> toReturn = new LinkedHashMap<>();
        // Mapping the record field-by-field from data source schema to PMML schema
        for (InputField inputField : evaluator.getInputFields()) {
            FieldName inputName = inputField.getName();
            Object rawValue = ((Double)INPUT_DATA.get(inputName.getValue()) +0.1);
            // Transforming an arbitrary user-supplied value to a known-good PMML value
            FieldValue inputValue = inputField.prepare(rawValue);
            toReturn.put(inputName, inputValue);
        }
        return toReturn;
    }

    private static void evaluate(ModelEvaluator<?> evaluator, Map<FieldName, FieldValue> arguments, AtomicInteger counter) {
        System.out.println(evaluator.evaluate(arguments).hashCode());
        System.out.println(counter.addAndGet(1));
    }

}
