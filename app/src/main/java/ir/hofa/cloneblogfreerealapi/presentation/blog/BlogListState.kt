package ir.hofa.cloneblogfreerealapi.presentation.blog

import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem

data class BlogListState(
    val isLoading: Boolean = false,
    val blog: List<BlogItem> = emptyList(),
    val error: String = ""
)