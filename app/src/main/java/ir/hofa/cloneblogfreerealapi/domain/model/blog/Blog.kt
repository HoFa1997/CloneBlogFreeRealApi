package ir.hofa.cloneblogfreerealapi.domain.model.blog

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Blog(
    val status: Int,
    val success: Boolean,
    val blogs: List<BlogItem>
)

