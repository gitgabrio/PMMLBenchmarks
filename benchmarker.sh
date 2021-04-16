#!/bin/bash

echo "*********"
echo "Overall install"
mvn clean install
echo "*********"
echo "Start Benchmarks"
echo "*********"
echo "----------"
echo "JPMML"
cd ./jpmml-benchmark
java -jar target/jpmml-benchmarks.jar -jvmArgs "-Xms4g -Xmx4g" -foe true -rf json -rff jpmml-results.json SimpleLinearRegressionBenchmark
echo "----------"
echo "TRUSTY - NO DROOLS"
cd ../trusty-nodrools-benchmark
java -jar target/trusty-nodrools-benchmark.jar -jvmArgs "-Xms4g -Xmx4g" -foe true -rf json -rff trusty-nodrools-results.json SimpleLinearRegressionBenchmark
echo "----------"
echo "TRUSTY - DROOLS"
cd ../trusty-drools-benchmark
java -jar target/trusty-drools-benchmarks.jar -jvmArgs "-Xms4g -Xmx4g" -foe true -rf json -rff trusty-drools-results.json SimpleLinearRegressionBenchmark
echo "----------"
echo "*********"
echo "All done! Bye"
echo "*********"