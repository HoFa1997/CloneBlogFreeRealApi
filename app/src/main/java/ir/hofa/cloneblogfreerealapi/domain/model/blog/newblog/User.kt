package ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "email")
    val email: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "name")
    val name: String
)