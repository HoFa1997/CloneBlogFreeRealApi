package ir.hofa.cloneblogfreerealapi.data.remote

import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface FreeRealAPI {

    @POST("auth/login")
    suspend fun reqLoginUser(
        @Body reqLoginBodyVM: ReqLoginUserVM
    ): ResLoginUserVM


    @GET("panel/blogs")
    suspend fun getBlog(
        @Header("Authorization") Bearer: String?
    ): Blog
}

