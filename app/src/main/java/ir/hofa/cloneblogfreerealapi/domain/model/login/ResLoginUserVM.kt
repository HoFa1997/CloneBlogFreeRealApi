package ir.hofa.cloneblogfreerealapi.domain.model.login


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResLoginUserVM(
    @Json(name = "error")
    val error: String = "",
    @Json(name = "status")
    val status: Int,
    @Json(name = "success")
    var success: Boolean = false,
    @Json(name = "token")
    val token: String
)