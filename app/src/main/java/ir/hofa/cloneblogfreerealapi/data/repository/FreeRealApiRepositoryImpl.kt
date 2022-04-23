package ir.hofa.cloneblogfreerealapi.data.repository

import ir.hofa.cloneblogfreerealapi.common.Constants
import ir.hofa.cloneblogfreerealapi.data.remote.FreeRealAPI
import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository

class FreeRealApiRepositoryImpl(
    private val api: FreeRealAPI
) : FreeRealApiRepository {


    override suspend fun getBlog(): Blog = api.getBlog(Constants.TOKEN)


}