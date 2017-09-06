#### Abi Parser

#### Setup of Sample App
* Generate the Solidity types:
  - `./gradlew :abi-data-types-generator:runSolidityTypeGenerator`
* Add library artifacts to local maven:
  - `./gradlew :abi-data-types:uploadArchives`
  - `./gradlew :abi-parser:uploadArchives`
  - `./gradlew :abi-parser-gradle-plugin:uploadArchives`
  
* Uncomment `include ':sample:app'` in the `settings.gradle` to include sample app module.

* Build sample app. This should also generate the class `MultiSigWalletWithDailyLimit`