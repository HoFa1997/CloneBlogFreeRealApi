package ir.hofa.cloneblogfreerealapi.presentation.register.components

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hofa.cloneblogfreerealapi.common.Resource
import ir.hofa.cloneblogfreerealapi.domain.model.register.ReqRegisterUser
import ir.hofa.cloneblogfreerealapi.domain.use_case.register.ReqUserRegister
import ir.hofa.cloneblogfreerealapi.navigation.Navigator
import ir.hofa.cloneblogfreerealapi.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val useCase: ReqUserRegister,
    private val spLocalLogin: SharedPreferences,

) : ViewModel() {


    private val _state = mutableStateOf(RegisterUserState())
    val userState: State<RegisterUserState> = _state


    fun reqUserRegister(body: ReqRegisterUser) {
        useCase(body).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RegisterUserState(data = result.data)
                    val tokenVM = _state.value.data?.token
                    spLocalLogin.edit().putString("token", tokenVM).apply()
                }
                is Resource.Error -> {
                    _state.value = RegisterUserState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = RegisterUserState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}

