package ir.hofa.cloneblogfreerealapi.presentation.login.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hofa.cloneblogfreerealapi.common.Constants
import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.use_case.login.ReqUserLogin
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReqUserLoginViewModel @Inject constructor(
    private val useCase: ReqUserLogin,
) : ViewModel() {

    private val _state = mutableStateOf(ReqUserLoginState())
    val state: State<ReqUserLoginState> = _state

//init {
//    reqUserLogin(ReqLoginUserVM(
//        email = "hfatemi1997@gmail.com",
//        password = "Fatemi1375"
//    ))
//}

    fun reqUserLogin(body:ReqLoginUserVM) {
        useCase(body).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ReqUserLoginState(token = result.data)

                }
                is Resource.Error -> {
                    _state.value = ReqUserLoginState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = ReqUserLoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}

