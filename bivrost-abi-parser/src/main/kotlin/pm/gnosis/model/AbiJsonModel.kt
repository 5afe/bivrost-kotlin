package pm.gnosis.model

import com.squareup.moshi.Json

class AbiRoot(@Json(name = "abi") val abi: List<AbiElementJson>,
              @Json(name = "contract_name") val contractName: String)

class AbiElementJson(@Json(name = "constant") val constant: Boolean,
                     @Json(name = "inputs") val inputs: List<ParameterJson>,
                     @Json(name = "name") val name: String,
                     @Json(name = "outputs") val outputs: List<ParameterJson>,
                     @Json(name = "payable") val payable: Boolean,
                     @Json(name = "type") val type: String)

class ParameterJson(@Json(name = "name") val name: String,
                @Json(name = "type") val type: String,
                @Json(name = "components") val components: List<ParameterJson>?)
