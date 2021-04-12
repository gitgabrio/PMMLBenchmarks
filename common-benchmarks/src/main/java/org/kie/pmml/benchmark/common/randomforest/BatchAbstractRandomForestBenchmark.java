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
package org.kie.pmml.benchmark.common.randomforest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BatchAbstractRandomForestBenchmark extends AbstractRandomForestBenchmark {

    public static final List<String> FIELDS = Arrays.asList("Age", "MonthlySalary", "TotalAsset", "TotalRequired", "NumberInstallments");
    public static final List<Object[]> INPUT_DATA;

    static {
        INPUT_DATA = Arrays.asList(new Object[][]{
                {40.83, 3.5, 0.5, 0.12, 0.11},
                {32.25, 1.5, 0.25, 122.11, 0.11},
                {28.17, 0.585, 0.04, 1004.11, 1.11},
                {29.75, 0.665, 0.25, 0.11, 0.11}
        });
    }

    public static Map<String, Object> getObjectsMap(final int index) {
        final Object[] rawObjects = INPUT_DATA.get(index);
        final Map<String, Object> toReturn = new HashMap<>();
        for (int i = 0; i < rawObjects.length; i ++) {
            toReturn.put(FIELDS.get(i), rawObjects[i]);
        }
        return toReturn;
    }
}
