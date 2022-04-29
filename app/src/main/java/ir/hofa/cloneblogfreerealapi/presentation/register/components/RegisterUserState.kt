package ir.hofa.cloneblogfreerealapi.presentation.register.components

import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM
import ir.hofa.cloneblogfreerealapi.domain.model.register.ResRegisterUser

data class RegisterUserState(
    val isLoading: Boolean = false,
    val data: ResRegisterUser? = null,
    val error: String = ""
)