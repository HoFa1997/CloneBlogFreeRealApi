package ir.hofa.cloneblogfreerealapi.domain.use_case.login

import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReqUserLogin @Inject constructor(
    private val repository: FreeRealApiRepository
) {

    operator fun invoke(body: ReqLoginUserVM): Flow<Resource<ResLoginUserVM>> = flow {
        try {
            emit(Resource.Loading<ResLoginUserVM>())
            val listBlog = repository.reqLoginUser(body)
            emit(Resource.Success<ResLoginUserVM>(listBlog))
        } catch (e: HttpException) {
            emit(
                Resource.Error<ResLoginUserVM>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<ResLoginUserVM>("Couldn't reach server. Check your internet connection."))
        }
    }

}