package ir.hofa.cloneblogfreerealapi.data.remote

import ir.hofa.cloneblogfreerealapi.common.Constants
import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.ResBlog
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.register.ReqRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.model.register.ResRegisterUser
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface FreeRealAPI {

    @POST("auth/login")
    suspend fun reqLoginUser(
        @Body reqLoginBodyVM: ReqLoginUserVM
    ): ResLoginUserVM

    @POST("auth/register")
    suspend fun reqRegisterUser(
        @Body reqRegisterUser: ReqRegisterUser
    ): ResRegisterUser


    @GET("panel/blogs")
    suspend fun getBlog(
        @Header("Authorization") Bearer: String?
    ): Blog

    @DELETE("panel/blogs/{blogID}")
    suspend fun deleteBlog(
        @Header("Authorization") Bearer: String?,
        @Path("blogID") blogID: String
    ): ResBlog

    @Multipart
    @POST("panel/blogs/")
    suspend fun newBlog(
        @Header("Authorization") Bearer: String?,
        @Part file: MultipartBody.Part,
        @Part(Constants.TITLE_ID) title: RequestBody,
        @Part(Constants.TAGS_ID) tags: RequestBody,
        @Part(Constants.TEXT_ID) text: RequestBody,
    ): ResBlog


}

