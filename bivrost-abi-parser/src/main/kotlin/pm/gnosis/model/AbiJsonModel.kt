package pm.gnosis.model

import com.squareup.moshi.Json

class AbiRoot(@Json(name = "abi") val abi: List<AbiElementJson>,
              @Json(name = "contract_name") val contractName: String)

class AbiElementJson(@Json(name = "constant") val constant: Boolean = false,
                     @Json(name = "inputs") val inputs: List<ParameterJson> = listOf(),
                     @Json(name = "name") val name: String = "",
                     @Json(name = "outputs") val outputs: List<ParameterJson> = listOf(),
                     @Json(name = "payable") val payable: Boolean = false,
                     @Json(name = "type") val type: String = "function",
                     @Json(name = "anonymous") val anonymous: Boolean = false)

class ParameterJson(@Json(name = "name") val name: String,
                    @Json(name = "type") val type: String,
                    @Json(name = "components") val components: List<ParameterJson>? = null,
                    @Json(name = "indexed") val indexed: Boolean = false)
