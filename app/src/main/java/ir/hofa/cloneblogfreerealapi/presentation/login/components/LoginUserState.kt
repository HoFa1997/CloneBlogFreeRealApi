package ir.hofa.cloneblogfreerealapi.presentation.login.components

import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM

data class LoginUserState(
    val isLoading: Boolean = false,
    val data: ResLoginUserVM? = null,
    val error: String = ""
)