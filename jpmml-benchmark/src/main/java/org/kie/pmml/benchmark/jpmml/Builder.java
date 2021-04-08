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

import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.jpmml.evaluator.ModelEvaluator;
import org.jpmml.model.visitors.VisitorBattery;

import static org.kie.pmml.benchmark.common.AbstractBenchmark.PMML_FILE;

public class Builder {

    private static ModelEvaluator<?> INSTANCE;

    private Builder() {
    }

    public static synchronized ModelEvaluator<?> getModelEvaluator() {
       try {
           if (INSTANCE == null) {
               INSTANCE = getModelEvaluatorInternal();
           }
           return INSTANCE;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }

    private static ModelEvaluator<?> getModelEvaluatorInternal() throws Exception {
        System.out.println("setup evaluator...");
        // Building a model evaluator from a PMML file
        ModelEvaluator<?> toReturn = new LoadingModelEvaluatorBuilder()
                .setLocatable(false)
                .setVisitors(new VisitorBattery())
                .load(PMML_FILE)
                .build();
        // Performing the self-check
        toReturn.verify();
        System.out.println("evaluator " + toReturn);
        return toReturn;
    }

}
