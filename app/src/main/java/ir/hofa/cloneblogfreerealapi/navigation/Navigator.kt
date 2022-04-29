package ir.hofa.cloneblogfreerealapi.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class Navigator @Inject constructor() {
    //TODO : CHANGE START DISTINCTION
    var destination: MutableStateFlow<NavigationDestination> = MutableStateFlow(Screen.LoginScreen)

    fun navigate(destination: NavigationDestination) {
        this.destination.value = destination
    }

}