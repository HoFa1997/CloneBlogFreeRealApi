package ir.hofa.cloneblogfreerealapi.data.repository

import ir.hofa.cloneblogfreerealapi.data.remote.FreeRealAPI
import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.NewBlog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.ResBlog
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.register.ReqRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.model.register.ResRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository


class FreeRealApiRepositoryImpl(
    private val api: FreeRealAPI
) : FreeRealApiRepository {


    override suspend fun getBlog(token: String): Blog =
        api.getBlog(
            "Bearer $token"
        )

    override suspend fun reqLoginUser(body: ReqLoginUserVM): ResLoginUserVM =
        api.reqLoginUser(body)

    override suspend fun reqRegisterUser(body: ReqRegisterUser): ResRegisterUser =
        api.reqRegisterUser(body)

    override suspend fun reqNewBlog(token: String, body: NewBlog): ResBlog =
        api.newBlog(
            "Bearer $token",
            body.image,
            body.title,
            body.tags,
            body.text
        )

    override suspend fun deleteBlog(token: String, blogId: String): ResBlog =
        api.deleteBlog(
            "Bearer $token",
            blogId
        )
}