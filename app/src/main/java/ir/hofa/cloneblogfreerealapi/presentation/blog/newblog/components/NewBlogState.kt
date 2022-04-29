package ir.hofa.cloneblogfreerealapi.presentation.blog.newblog.components

import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.ResNewBlog

data class NewBlogState(
    val isLoading: Boolean = false,
    val blog: ResNewBlog? = null,
    val error: String = ""
)