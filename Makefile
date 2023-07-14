build-SpringCloudFunctionLambda:
	./gradlew --no-daemon clean nativeCompile
	echo '#!/bin/sh' > ./build/bootstrap
	echo 'set -euo pipefail' >> ./build/bootstrap # Exit immediately if any command fails, propagate errors in pipelines.
	echo './aws-spring-cloud-function-kotlin-reactive-sample' >> ./build/bootstrap
	chmod +x ./build/bootstrap
	cp ./build/bootstrap $(ARTIFACTS_DIR)
	cp ./build/native/nativeCompile/aws-spring-cloud-function-kotlin-reactive-sample $(ARTIFACTS_DIR)