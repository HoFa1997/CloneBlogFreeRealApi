package ir.hofa.cloneblogfreerealapi.presentation

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.hofa.cloneblogfreerealapi.navigation.Navigation
import ir.hofa.cloneblogfreerealapi.presentation.ui.theme.CloneBlogFreeRealApiTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var spLocalBlog: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CloneBlogFreeRealApiTheme {
                Scaffold {
                    val navController = rememberNavController()
                    Navigation(navController)
                }
            }
        }
    }

    override fun onDestroy() {
        spLocalBlog.edit().clear().apply()
        super.onDestroy()
    }


}