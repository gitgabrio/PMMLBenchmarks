PMML Benchmarks
===============

How to run the benchmarks
The benchmarks are written using JMH.

To run the benchmarks you need to:

Build the project with 
    
    mvn clean install

You can define several JMH parameters for executing the benchmarks:

    -jvm - Custom JVM to use when forking (path to JVM executable). JMH can create new JVM fork for a set of benchmark iterations.
    -jvmArgs - Custom JVM parameters. E.g. you can specify memory consumption arguments here.
    -gc - Option to force JMH to run garbage collection between benchmark iterations. Boolean parameter.
    -foe - Option to determine if JMH should fail immediately if any benchmark had experienced some unrecoverable error. Boolean parameter.
    -wi - Number of warmup iterations - Should be used only for "tweaking" the benchmarks run. Default value that is recommended to be used is set directly in the benchmarks' code using annotation @Warmup.
    -i - Number of measurement iterations - Should be used only for "tweaking" the benchmarks run. Default value that is recommended to be used is set directly in the benchmarks' code using annotation @Measurement.
    -f - Number that defines how many times should be a single benchmark forked. This defines how many JVM forks are used for measuring single benchmark. Default JMH value is 10.
    -rf - Results format type. E.g. csv, json.
    -rff - Filename to which results are written.

You can also define a wildcard pattern, that specifies, which benchmarks should be run.
Examples

Running trusty SimpleBenchmark and storing results in file trusty-results.json:

    cd trusty-benchmarks
    java -jar target/trusty-benchmarks.jar -jvmArgs "-Xms4g -Xmx4g" -foe true -rf json -rff trusty-results.json SimpleBenchmark

Running trusty SimpleBenchmark and storing results in file trusty-results.json:

    cd jpmml-benchmarks
    java -jar target/jpmml-benchmarks.jar -jvmArgs "-Xms4g -Xmx4g" -foe true -rf json -rff jpmml-results.json SimpleBenchmark

