package ir.hofa.cloneblogfreerealapi.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import ir.hofa.cloneblogfreerealapi.presentation.blog.BlogScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavHostController) {
    BlogScreen(navController)
}


