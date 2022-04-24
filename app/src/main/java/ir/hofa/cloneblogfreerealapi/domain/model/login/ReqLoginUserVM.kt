package ir.hofa.cloneblogfreerealapi.domain.model.login


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReqLoginUserVM(
    @Json(name = "email")
    val email: String,
    @Json(name = "password")
    val password: String
)
