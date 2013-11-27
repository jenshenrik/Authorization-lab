#java -classpath .:bin/:SampleAzn.jar:SampleAction.jar:SampleLM.jar -Djava.security.manager -Djava.security.policy==sampleazn.policy -Djava.security.auth.login.config==sample_jaas.config sample.SampleAzn

java -classpath SampleAzn.jar:SampleAction.jar:SampleLM.jar -Djava.security.manager -Djava.security.policy==sampleazn.policy -Djava.security.auth.login.config==sample_jaas.config sample.SampleAzn
