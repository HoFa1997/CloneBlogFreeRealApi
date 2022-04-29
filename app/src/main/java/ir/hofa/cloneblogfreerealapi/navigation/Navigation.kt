package ir.hofa.cloneblogfreerealapi.navigation

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem
import ir.hofa.cloneblogfreerealapi.presentation.HomeScreen
import ir.hofa.cloneblogfreerealapi.presentation.blog.DetailBlog
import ir.hofa.cloneblogfreerealapi.presentation.blog.NewBlogScreen
import ir.hofa.cloneblogfreerealapi.presentation.login.LoginScreen
import ir.hofa.cloneblogfreerealapi.presentation.login.LoginUserViewModel
import ir.hofa.cloneblogfreerealapi.presentation.register.RegisterScreen

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    userViewModel: LoginUserViewModel = hiltViewModel()
) {
    val navigator = userViewModel.navigator
    val navController = rememberNavController()
    val destination by navigator.destination.collectAsState()

    LaunchedEffect(destination) {
        if (navController.currentDestination?.route != destination.route) {
            navController.navigate(destination.route)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = Screen.NewBlog.route) {
            NewBlogScreen(navController)
        }
        composable(
//            route = Screen.DetailBlog.route + "/{id}",
//            arguments = listOf(
//                navArgument("id") {
//                    type = NavType.StringType
//                    defaultValue = null
//                    nullable = true
//                }
//            )
            route = Screen.DetailBlog.route + "/{id}",
        ) { backStackEntry ->

            val blogItemObject =
                navController.previousBackStackEntry?.arguments?.getParcelable<BlogItem>("id")

            DetailBlog(blogItemObject, navController)
        }
    }
}