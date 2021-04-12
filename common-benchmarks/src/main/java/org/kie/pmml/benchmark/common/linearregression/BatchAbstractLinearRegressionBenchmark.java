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
package org.kie.pmml.benchmark.common.linearregression;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BatchAbstractLinearRegressionBenchmark extends AbstractLinearRegressionBenchmark {

    public static final List<String> FIELDS = Arrays.asList("age", "salary", "car_location");
    public static final List<Object[]> INPUT_DATA;

    static {
        INPUT_DATA = Arrays.asList(new Object[][]{
                {27.0, 34000.0, "street"},
                {49.0, 78000.0, "carpark"},
                {57.0, 72000.0, "street", 1582.1},
                {61.0, 123000.0, "carpark", 1836.5699999999997}
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
