# Rampage
Library for advance motion control

### How to use

Add it in your build.gradle in repositories:

Make a file in root called `gpr.gradle` then generate token from github in 
order to read packages from the repos and add it here.

ADD THIS TO `.gitignore`

```gradle
  ext {
    user=<USERNAME>
    key=<TOKEN>
  }
```

Inside `build.gradle`

```gradle
  
  apply from: 'gpr.gradle'
  
  repositories {
    maven {
      url = uri("https://maven.pkg.github.com/rambots/rampage")
        credentials {
            username = user
            password = key
        }
    }
  }
```



Add the dependency

```gradle
dependencies {
  implementation 'com.rambots4571:rampage:2020.+'
}
```
