```aidl
gradle muti block with security settings
```
```aidl
本地jar 包打包到项目工程中
根目录下 新建lib 目录  放入要打包的jar 
mvn install:install-file -Dfile=oos-sdk-6.1.0.jar -DgroupId=oos.sdk -DartifactId=com.amazonaws -Dversion=1.0.0 -Dpackaging=jar


```