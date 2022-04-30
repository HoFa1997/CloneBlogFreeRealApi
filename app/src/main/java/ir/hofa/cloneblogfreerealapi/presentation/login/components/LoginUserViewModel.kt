package ir.hofa.cloneblogfreerealapi.presentation.login.components

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.use_case.login.ReqUserLogin
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginUserViewModel @Inject constructor(
    private val useCase: ReqUserLogin,
    private val spLocalLogin: SharedPreferences,
) : ViewModel() {


    private val _state = mutableStateOf(LoginUserState())
    val userState: State<LoginUserState> = _state


    fun reqUserLogin(body: ReqLoginUserVM) {
        useCase(body).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = LoginUserState(data = result.data)
                    val tokenVM = _state.value.data?.token
                    spLocalLogin.edit().putString("token", tokenVM).apply()
                }
                is Resource.Error -> {
                    _state.value = LoginUserState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    spLocalLogin.edit().remove("token").apply()
                    _state.value = LoginUserState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}

