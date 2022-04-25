package ir.hofa.cloneblogfreerealapi.presentation.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class Navigator @Inject constructor() {

    var destination: MutableStateFlow<NavigationDestination> = MutableStateFlow(Screen.LoginScreen)

    fun navigate(destination: NavigationDestination) {
        this.destination.value = destination
    }

}