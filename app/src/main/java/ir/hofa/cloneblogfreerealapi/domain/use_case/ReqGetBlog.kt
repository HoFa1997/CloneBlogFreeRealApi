package ir.hofa.cloneblogfreerealapi.domain.use_case

import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReqGetBlog @Inject constructor(
    private val repository: FreeRealApiRepository
) {
    operator fun invoke(): Flow<Resource<Blog>> = flow {
        try {
            emit(Resource.Loading<Blog>())
            val listBlog = repository.getBlog()
            emit(Resource.Success<Blog>(listBlog))
        } catch (e: HttpException) {
            emit(Resource.Error<Blog>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Blog>("Couldn't reach server. Check your internet connection."))
        }
    }
}