package ir.hofa.cloneblogfreerealapi.navigation

interface NavigationDestination {
    val route: String
}

sealed class Screen(override val route: String) : NavigationDestination {
    object LoginScreen : Screen("login")
    object HomeScreen : Screen("home")
    object RegisterScreen : Screen("register")
    object NewBlog : Screen("newBlog")

}

