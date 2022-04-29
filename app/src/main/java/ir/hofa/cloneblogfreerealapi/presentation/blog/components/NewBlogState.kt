package ir.hofa.cloneblogfreerealapi.presentation.blog.components

import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.ResBlog

data class NewBlogState(
    val isLoading: Boolean = false,
    val blog: ResBlog? = null,
    val error: String = ""
)