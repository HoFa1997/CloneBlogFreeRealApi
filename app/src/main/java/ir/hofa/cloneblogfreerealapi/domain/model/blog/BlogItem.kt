package ir.hofa.cloneblogfreerealapi.domain.model.blog


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class BlogItem(
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "_id")
    val _id: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "tags")
    val tags: List<String>,
    @Json(name = "text")
    val text: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
//    @Json(name = "user")
//    val user: User,
    @Json(name = "__v")
    val v: Int
) : Parcelable