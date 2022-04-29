package ir.hofa.cloneblogfreerealapi.domain.repository

import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.NewBlog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.ResBlog
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.register.ReqRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.model.register.ResRegisterUser


interface FreeRealApiRepository {


    suspend fun getBlog(token: String): Blog

    suspend fun reqLoginUser(body: ReqLoginUserVM): ResLoginUserVM

    suspend fun reqRegisterUser(body: ReqRegisterUser): ResRegisterUser

    suspend fun reqNewBlog(token: String, body: NewBlog): ResBlog

    suspend fun deleteBlog(token: String, blogId: String): ResBlog
}