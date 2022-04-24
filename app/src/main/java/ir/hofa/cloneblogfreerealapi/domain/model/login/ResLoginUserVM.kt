package ir.hofa.cloneblogfreerealapi.domain.model.login


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResLoginUserVM(
    @Json(name = "status")
    val status: Int,
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "token")
    val token: String
)