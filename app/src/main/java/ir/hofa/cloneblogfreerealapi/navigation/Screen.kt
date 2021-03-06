package ir.hofa.cloneblogfreerealapi.navigation

interface NavigationDestination {
    val route: String
}

sealed class Screen(override val route: String) : NavigationDestination {
    object LoginScreen : Screen("login")
    object RegisterScreen : Screen("register")
    object NewBlog : Screen("newBlog")
    object DetailBlog : Screen("detailBlog")
    object BlogScreen : Screen("blog")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}


