# seung-ramyeon
Rest API Spring Boot Application

### Gradle

##### Configuration

```
$ cp settings.gradle.template settings.gradle
$ vi settings.gradle
...
rootProject.name = 'seung-ramyeon'

include "seung-kimchi"
project(":seung-kimchi").projectDir = new File($PATH)
...
```

##### Build

```
$ gradlew build --refresh-dependencies
```

##### JVM Options

```
-Xms256m
-Xmx256m
-Dlog.path=w:/logs
```
