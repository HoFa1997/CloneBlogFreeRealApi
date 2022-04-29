package ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import okhttp3.MultipartBody
import okhttp3.RequestBody

@JsonClass(generateAdapter = true)
data class NewBlog(
    @Json(name = "image")
    val image: MultipartBody.Part,
    @Json(name = "title")
    val title: RequestBody,
    @Json(name = "tags")
    val tags: RequestBody,
    @Json(name = "text")
    val text: RequestBody

)
