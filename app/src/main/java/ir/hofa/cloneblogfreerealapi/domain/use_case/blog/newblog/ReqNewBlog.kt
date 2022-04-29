package ir.hofa.cloneblogfreerealapi.domain.use_case.blog.newblog


import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.blog.Blog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.NewBlog
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.ResNewBlog
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReqNewBlog @Inject constructor(
    private val repository: FreeRealApiRepository
) {
    operator fun invoke(body:NewBlog): Flow<Resource<ResNewBlog>> = flow {
        try {
            emit(Resource.Loading<ResNewBlog>())
            val listBlog = repository.reqNewBlog(body)
            emit(Resource.Success<ResNewBlog>(listBlog))
        } catch (e: HttpException) {
            emit(Resource.Error<ResNewBlog>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<ResNewBlog>("Couldn't reach server. Check your internet connection."))
        }
    }
}