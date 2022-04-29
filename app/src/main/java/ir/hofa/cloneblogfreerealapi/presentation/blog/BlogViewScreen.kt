package ir.hofa.cloneblogfreerealapi.presentation.blog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hofa.cloneblogfreerealapi.navigation.Screen
import ir.hofa.cloneblogfreerealapi.presentation.blog.components.BlogListViewModel
import ir.hofa.cloneblogfreerealapi.presentation.ui.theme.primaryLightColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogScreen(
    navController: NavHostController,
    viewModel: BlogListViewModel = hiltViewModel(),
) {
    val state = viewModel.stateGetBlog.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.blog.isEmpty()) {
            Text(
                style = TextStyle(fontSize = 40.sp),
                text = "PLS ADD NEW BLOG",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxHeight()
            ) {
                items(state.blog) { items ->
                    BlogListView(items,navController)
                }
            }
        }
        if (state.error.isNotBlank()) {
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
}