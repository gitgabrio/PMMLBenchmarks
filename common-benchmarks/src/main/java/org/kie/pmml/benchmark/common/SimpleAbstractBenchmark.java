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
package org.kie.pmml.benchmark.common;

import java.util.HashMap;
import java.util.Map;

public abstract class SimpleAbstractBenchmark extends AbstractBenchmark {

    public static final Map<String, Object> INPUT_DATA;

    static {
        INPUT_DATA = new HashMap<>();
        INPUT_DATA.put("Age", 40.83);
        INPUT_DATA.put("MonthlySalary", 3.5);
        INPUT_DATA.put("TotalAsset", 0.04);
        INPUT_DATA.put("TotalRequired", 10.04);
        INPUT_DATA.put("NumberInstallments", 93.2);
    }
}
