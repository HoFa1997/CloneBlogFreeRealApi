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
import ir.hofa.cloneblogfreerealapi.presentation.navigation.Navigator
import ir.hofa.cloneblogfreerealapi.presentation.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReqUserLoginViewModel @Inject constructor(
    private val useCase: ReqUserLogin,
    private val spLocalLogin: SharedPreferences,
    var navigator: Navigator
) : ViewModel() {


    private val _state = mutableStateOf(ReqUserLoginState())
    val state: State<ReqUserLoginState> = _state


    fun reqUserLogin(body: ReqLoginUserVM) {
        useCase(body).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ReqUserLoginState(token = result.data)
                    val tokenVM = _state.value.token?.token
                    spLocalLogin.edit().putString("token", tokenVM).apply()
                    navigator.navigate(Screen.HomeScreen)
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

