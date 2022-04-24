package ir.hofa.cloneblogfreerealapi.domain.repository

import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM


interface FreeRealApiRepository {

    suspend fun getBlog(): Blog

    suspend fun reqLoginUser(body: ReqLoginUserVM): ResLoginUserVM
}