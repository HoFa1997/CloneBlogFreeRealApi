package ir.hofa.cloneblogfreerealapi.data.repository

import ir.hofa.cloneblogfreerealapi.common.Constants
import ir.hofa.cloneblogfreerealapi.data.remote.FreeRealAPI
import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository

class FreeRealApiRepositoryImpl(
    private val api: FreeRealAPI
) : FreeRealApiRepository {


    override suspend fun getBlog(token:String): Blog =
        api.getBlog(
            "Bearer $token"
        )

    override suspend fun reqLoginUser(body: ReqLoginUserVM): ResLoginUserVM =
        api.reqLoginUser(body)


}