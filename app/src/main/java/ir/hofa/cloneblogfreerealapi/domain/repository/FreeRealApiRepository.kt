package ir.hofa.cloneblogfreerealapi.domain.repository

import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem

interface FreeRealApiRepository {

    suspend fun getBlog(): Blog
}