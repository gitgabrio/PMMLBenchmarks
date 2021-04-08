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
package org.kie.pmml.benchmark.trusty;

import org.kie.pmml.api.runtime.PMMLRuntime;
import org.kie.pmml.evaluator.assembler.factories.PMMLRuntimeFactoryImpl;

import static org.kie.pmml.benchmark.common.AbstractBenchmark.PMML_FILE;

public class Builder {

//    public static final String GROUP_ID = "org.kie.pmml";
//    public static final String ARTIFACT_ID = "trusty-kjar";
//    public static final String VERSION = "1.0-SNAPSHOT";
//    public static final String GAV = String.format("%s:%s:%s", GROUP_ID, ARTIFACT_ID, VERSION);
//    public static final ReleaseId RELEASE_ID = new ReleaseIdImpl(GROUP_ID, ARTIFACT_ID, VERSION);

    private static final String EXTERNALISECANONICALMODELLAMBDA = "drools.externaliseCanonicalModelLambda";
    private static PMMLRuntime INSTANCE;


    static {
        System.setProperty(EXTERNALISECANONICALMODELLAMBDA, "false");
        System.out.println(EXTERNALISECANONICALMODELLAMBDA + ": " +   System.getProperty(EXTERNALISECANONICALMODELLAMBDA));
        System.out.println("Creating INSTANCE...");
        INSTANCE = new PMMLRuntimeFactoryImpl().getPMMLRuntimeFromFile(PMML_FILE);
        System.out.println("INSTANCE " + INSTANCE);
    }


    private Builder() {
    }


    public static synchronized PMMLRuntime getPMMLRuntime() {
//        try {
//            if (INSTANCE == null) {
//                INSTANCE = getPMMLRuntimeInternal();
//            }
            return INSTANCE;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
    }

//    private static PMMLRuntime getPMMLRuntimeInternal() {
//        System.out.println("setup pmmlRuntime...");
//        PMMLRuntime toReturn = KieRuntimeFactory.of(kieContainer.getKieBase()).get(PMMLRuntime.class);
//        System.out.println("pmmlRuntime " + toReturn);
//        return toReturn;
//    }
}
