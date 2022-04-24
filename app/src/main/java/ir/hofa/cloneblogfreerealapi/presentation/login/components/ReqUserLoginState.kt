package ir.hofa.cloneblogfreerealapi.presentation.login.components

import ir.hofa.cloneblogfreerealapi.domain.model.login.ResLoginUserVM

data class ReqUserLoginState(
    val isLoading: Boolean = false,
    val token: ResLoginUserVM? = null,
    val error: String = ""
)