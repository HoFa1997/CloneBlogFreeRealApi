package ir.hofa.cloneblogfreerealapi.domain.use_case.blog

import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.ResBlog
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class ReqDelBlog @Inject constructor(
    private val repository: FreeRealApiRepository
) {
    operator fun invoke(token: String, blogId: String): Flow<Resource<ResBlog>> = flow {
        try {
            emit(Resource.Loading<ResBlog>())
            val listBlog = repository.deleteBlog(token, blogId)
            emit(Resource.Success<ResBlog>(listBlog))
        } catch (e: HttpException) {
            emit(Resource.Error<ResBlog>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<ResBlog>("Couldn't reach server. Check your internet connection."))
        }
    }
}