# seung-ramyeon
Rest API Spring Boot Application

### Gradle

##### Configuration

```
$ cp settings.gradle.windows settings.gradle
$ vi settings.gradle
...
rootProject.name = 'seung-ramyeon'

include "seung-kimchi"
project(":seung-kimchi").projectDir = new File("../seung-kimchi)
...
$ cp build.gradle.windows build.gradle
$ vi build.gradle
```

##### Build

```
$ gradlew build --refresh-dependencies
```

##### JVM Options

```
-Xms256m
-Xmx256m
-Dlog.path=./log
```
