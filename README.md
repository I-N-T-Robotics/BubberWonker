# Rampage

Library for I.N.T Robotics

Contains useful code that will be used through multiple seasons.

### How to use

1. Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

2. Add the dependency

```gradle
dependencies {
	implementation 'com.github.I-N-T-Robotics:BubberWonker:v2024.1.0'
}
```

3. Install the necessary vendor dependencies

```gradle
https://maven.ctr-electronics.com/release/com/ctre/phoenix/Phoenix5-frc2023-latest.json

https://software-metadata.revrobotics.com/REVLib-2023.json

https://3015rangerrobotics.github.io/pathplannerlib/PathplannerLib.json
