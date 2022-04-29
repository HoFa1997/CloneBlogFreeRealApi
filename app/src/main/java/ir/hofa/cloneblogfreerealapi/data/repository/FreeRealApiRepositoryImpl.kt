package ir.hofa.cloneblogfreerealapi.data.repository

import android.R.attr.password
import ir.hofa.cloneblogfreerealapi.common.Constants
import ir.hofa.cloneblogfreerealapi.data.remote.FreeRealAPI
import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.NewBlog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.ResNewBlog
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.register.ReqRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.model.register.ResRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository
import okhttp3.MediaType
import okhttp3.MultipartBody.Part.Companion.create
import okhttp3.RequestBody


class FreeRealApiRepositoryImpl(
    private val api: FreeRealAPI
) : FreeRealApiRepository {

    //TODO: CHANGE TOKEN TO REAL TOKEN
    override suspend fun getBlog(token: String): Blog =
        api.getBlog(
            "Bearer $token"
        )
//    override suspend fun getBlog(token: String): Blog =
//        api.getBlog(
//            "Bearer ${Constants.TOKEN}"
//        )

    override suspend fun reqLoginUser(body: ReqLoginUserVM): ResLoginUserVM =
        api.reqLoginUser(body)

    override suspend fun reqRegisterUser(body: ReqRegisterUser): ResRegisterUser =
        api.reqRegisterUser(body)

    override suspend fun reqNewBlog(body: NewBlog): ResNewBlog =
        api.newBlog(
            "Bearer ${Constants.TOKEN}",
            body.image,
            body.title,
            body.tags,
            body.text
        )
}