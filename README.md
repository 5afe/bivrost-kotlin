### Bivrost for Kotlin

🔥 🌈 Bridge between Solidity Contracts and Kotlin

[![](https://jitpack.io/v/5afe/bivrost-kotlin.svg)](https://jitpack.io/#5afe/bivrost-kotlin)
[![Build Status](https://travis-ci.org/gnosis/bivrost-kotlin.svg?branch=master)](https://travis-ci.org/gnosis/bivrost-kotlin)

#### Include via jitpack

* Add jitpack repository (see https://www.jitpack.io/#5afe/bivrost-kotlin)

* Add classpath dependency:
```
classpath ('com.github.5afe.bivrost-kotlin:bivrost-gradle-plugin:<version>')
```

* Add runtime dependency:
```
implementation ('com.github.5afe.abi-kotlin:bivrost-solidity-types:<version>')
```

* Apply plugin:
```
apply plugin: 'bivrost'
```

* Add abi json to project in `app/abi` folder (see sample app)



#### Setup of Sample App
* Optional: Generate the Solidity types:
  - `./gradlew :bivrost-solidity-types-generator:runSolidityTypeGenerator`
* Add library artifacts to local maven:
  - `./gradlew :bivrost-utils:uploadArchives`
  - `./gradlew :bivrost-solidity-types:uploadArchives`
  - `./gradlew :bivrost-abi-parser:uploadArchives`
  - `./gradlew :bivrost-gradle-plugin:uploadArchives`
  
* Uncomment `include ':sample:app'` in the `settings.gradle` to include sample app module.

* Build sample app. This should also generate the class `MultiSigWalletWithDailyLimit`
