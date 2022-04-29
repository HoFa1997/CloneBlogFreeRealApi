package ir.hofa.cloneblogfreerealapi.domain.model.register


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReqRegisterUser(
    @Json(name = "email")
    val email: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "password")
    val password: String
)