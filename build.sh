echo "Building..."
javac sample/*.java sample/module/*.java sample/principal/*.java
echo "Done.\n\nPackaging .jars..."
echo "SampleAzn.jar:"
jar -cvf SampleAzn.jar sample/SampleAzn.class sample/MyCallbackHandler.class
echo "SampleAction.jar:"
jar -cvf SampleAction sample/SampleAction.class
echo "SampleLM.jar:"
jar -cvf SampleLM.jar sample/module/SampleLoginModule.class sample/principal/SamplePrincipal.class
echo "Done.\n\nAll done."