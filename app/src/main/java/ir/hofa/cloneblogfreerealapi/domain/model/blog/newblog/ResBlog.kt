package ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResBlog(
    @Json(name = "blog")
    val blog: Blog,
    @Json(name = "message")
    val message: String,
    @Json(name = "status")
    val status: Int,
    @Json(name = "success")
    var success: Boolean
)