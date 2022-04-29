package ir.hofa.cloneblogfreerealapi.presentation.blog

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem
import ir.hofa.cloneblogfreerealapi.navigation.Screen
import ir.hofa.cloneblogfreerealapi.presentation.blog.components.BlogListViewModel

@Composable
fun DetailBlog(
    blogItem: BlogItem?,
    navController: NavHostController,
    viewModel: BlogListViewModel = hiltViewModel(),
) {
    val state = viewModel.stateDelNewBlog.value

    Column() {
        Text(text = blogItem?.text ?: "ASD")
        Text(text = blogItem?.id ?: "ASD")
        Text(text = blogItem?._id ?: "ASD")
        Text(text = blogItem?.createdAt ?: "ASD")
        Text(text = blogItem?.image ?: "ASD")
        Text(text = blogItem?.title ?: "ASD")
        Button(onClick = {
            viewModel.reqDelBlog(blogItem?.id ?: "null")
        }) {
            Text(text = "Delete this blog")
        }
        when (state.blog?.success) {
            true -> {
                viewModel.getBlog()
                state.blog.success = false
                navController.navigate(Screen.HomeScreen.route)
            }
            else -> {
                Text(text = state.error)
            }
        }
    }

}