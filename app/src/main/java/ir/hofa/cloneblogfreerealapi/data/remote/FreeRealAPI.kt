package ir.hofa.cloneblogfreerealapi.data.remote

import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem
import retrofit2.http.*


interface FreeRealAPI {

//    @Headers("Content-Type: application/json")
//    @POST("auth/login")
//    suspend fun getToken(
//        @Body reqLoginBody: ReqLoginBody
//    ): Response<Token>

//@Headers("Authorization" ,"Bearer "+Constants.TOKEN )
    @GET("panel/blogs")
    suspend fun getBlog(
    @Header("Authorization") Bearer: String?
    ): Blog
}

