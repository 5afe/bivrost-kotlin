package pm.gnosis.model

import com.squareup.moshi.Json

class AbiRoot(@Json(name = "abi") val abi: List<AbiElementJson>,
              @Json(name = "contract_name") val contractName: String)

class AbiElementJson(@Json(name = "constant") val constant: Boolean,
                     @Json(name = "inputs") val inputs: List<InputJson>,
                     @Json(name = "name") val name: String,
                     @Json(name = "outputs") val outputs: List<OutputJson>,
                     @Json(name = "payable") val payable: Boolean,
                     @Json(name = "type") val type: String)

class InputJson(@Json(name = "name") val name: String,
                @Json(name = "type") val type: String)

class OutputJson(@Json(name = "name") val name: String,
                 @Json(name = "type") val type: String)
