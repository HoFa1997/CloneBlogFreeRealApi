package ir.hofa.cloneblogfreerealapi.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.hofa.cloneblogfreerealapi.presentation.blog.BlogListView
import ir.hofa.cloneblogfreerealapi.presentation.blog.components.BlogListViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: BlogListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
//            if (state.blog.isEmpty()){
            items(state.blog) {
                BlogListView(blog = it)
            }
//            }else{
//                items(state.blog) {
//                    BlogListView(blog = it)
//                }
//            }

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
    }
}


