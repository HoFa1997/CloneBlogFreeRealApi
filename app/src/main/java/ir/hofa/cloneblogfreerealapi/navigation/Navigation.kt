package ir.hofa.cloneblogfreerealapi.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem
import ir.hofa.cloneblogfreerealapi.presentation.blog.BlogScreen
import ir.hofa.cloneblogfreerealapi.presentation.blog.DetailBlog
import ir.hofa.cloneblogfreerealapi.presentation.blog.NewBlogScreen
import ir.hofa.cloneblogfreerealapi.presentation.login.LoginScreen
import ir.hofa.cloneblogfreerealapi.presentation.register.RegisterScreen

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(
            route = Screen.BlogScreen.route + "/{username}",
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                    defaultValue = "User"
                    nullable = true
                }
            )
        ) { entry ->
            BlogScreen(
                username = entry.arguments?.getString("username"),
                navController
            )
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = Screen.NewBlog.route) {
            NewBlogScreen(navController)
        }
        composable(
            route = Screen.DetailBlog.route + "/{id}",
        ) {
            val blogItemObject =
                navController.previousBackStackEntry?.arguments?.getParcelable<BlogItem>("id")

            DetailBlog(blogItemObject, navController)
        }
    }
}