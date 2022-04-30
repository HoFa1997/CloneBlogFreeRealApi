package ir.hofa.cloneblogfreerealapi.domain.use_case.register

import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.register.ReqRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.model.register.ResRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.repository.FreeRealApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReqUserRegister @Inject constructor(
    private val repository: FreeRealApiRepository
) {

    operator fun invoke(body: ReqRegisterUser): Flow<Resource<ResRegisterUser>> = flow {
        try {
            emit(Resource.Loading<ResRegisterUser>())
            val listBlog = repository.reqRegisterUser(body)
            emit(Resource.Success<ResRegisterUser>(listBlog))
        } catch (e: HttpException) {
            emit(Resource.Error<ResRegisterUser>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error<ResRegisterUser>("Couldn't reach server. Check your internet connection."))
        }
    }

}