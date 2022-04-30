package ir.hofa.cloneblogfreerealapi.domain.model.register


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResRegisterUser(
//    @Json(name = "error")
//    val error: String,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "success")
    var success: Boolean,
    @Json(name = "token")
    val token: String
)