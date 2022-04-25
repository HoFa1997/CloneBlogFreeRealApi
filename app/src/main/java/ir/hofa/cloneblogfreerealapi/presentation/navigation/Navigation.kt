package ir.hofa.cloneblogfreerealapi.presentation.navigation

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ir.hofa.cloneblogfreerealapi.presentation.HomeScreen
import ir.hofa.cloneblogfreerealapi.presentation.login.LoginScreen
import ir.hofa.cloneblogfreerealapi.presentation.login.components.ReqUserLoginViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    viewModel: ReqUserLoginViewModel = hiltViewModel()
) {
    val navigator = viewModel.navigator
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
            HomeScreen()
        }
    }
}