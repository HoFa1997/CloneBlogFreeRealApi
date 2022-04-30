package ir.hofa.cloneblogfreerealapi.presentation.blog

import android.app.Activity
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.scopes.ActivityScoped
import ir.hofa.cloneblogfreerealapi.R
import ir.hofa.cloneblogfreerealapi.common.Utils
import ir.hofa.cloneblogfreerealapi.navigation.Screen
import ir.hofa.cloneblogfreerealapi.presentation.blog.components.BlogListViewModel
import ir.hofa.cloneblogfreerealapi.presentation.ui.theme.primaryLightColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@ActivityScoped
fun BlogScreen(
    username: String?,
    navController: NavHostController,
    viewModel: BlogListViewModel = hiltViewModel()
) {
    val activity = (LocalContext.current as? Activity)
    val context = LocalContext.current

    Utils.USERNAME = username.toString()

    var doubleBackToExitPressedOnce = false
    BackHandler(true) {
        if (doubleBackToExitPressedOnce) {
            activity?.finish()
            return@BackHandler
        }
        doubleBackToExitPressedOnce = true
        Toast.makeText(
            context,
            "Please click BACK again to exit", Toast.LENGTH_SHORT
        ).show()

        Handler(Looper.getMainLooper()).postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

    val state = viewModel.stateGetBlog.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp)
    ) {
        when {
            state.error.isNotBlank() -> {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            state.blog.isEmpty() -> {
                Text(
                    style = TextStyle(fontSize = 40.sp),
                    text = "PLS ADD NEW BLOG",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(state.blog) { items ->
                        BlogListView(items, navController)
                    }
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 20.dp, bottom = 20.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.NewBlog.route)
                },
                backgroundColor = primaryLightColor
            ) {
                Text("+")
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(unbounded = true),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                color = Color.Red,
                text = "User : $username",
                fontSize = 16.sp
            )
        }
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate(Screen.LoginScreen.route)
            }) {
                Icon(
                    painter = painterResource(R.drawable.ic_exit),
                    null
                )
            }
        }
    }
}