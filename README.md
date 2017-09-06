#### Abi Parser


#### Include via jitpack

* Add jitpack repository (see https://www.jitpack.io/#gnosis/abi-kotlin)

* Add classpath dependency:
```
classpath ('com.github.gnosis:abi-kotlin:<version>') {
    exclude group: 'com.android.tools.build'
    exclude group: 'com.squareup'
    exclude group: 'com.squareup.moshi'
}
```

* Add runtime dependency:
```
implementation ('com.github.gnosis:abi-kotlin:<version>') {
    exclude group: 'com.android.tools.build'
    exclude group: 'com.squareup'
    exclude group: 'com.squareup.moshi'
}
```

* Apply plugin:
```
apply plugin: pm.gnosis.abiparser.plugin.AbiParserPlugin
```

* Add abi json to project in `app/abi` folder (see sample app)



#### Setup of Sample App
* Optional: Generate the Solidity types:
  - `./gradlew :abi-data-types-generator:runSolidityTypeGenerator`
* Add library artifacts to local maven:
  - `./gradlew :abi-data-types:uploadArchives`
  - `./gradlew :abi-parser:uploadArchives`
  - `./gradlew :abi-parser-gradle-plugin:uploadArchives`
  
* Uncomment `include ':sample:app'` in the `settings.gradle` to include sample app module.

* Build sample app. This should also generate the class `MultiSigWalletWithDailyLimit`