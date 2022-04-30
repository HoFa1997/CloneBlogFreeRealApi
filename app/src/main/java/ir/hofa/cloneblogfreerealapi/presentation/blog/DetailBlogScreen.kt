package ir.hofa.cloneblogfreerealapi.presentation.blog

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.skydoves.landscapist.glide.GlideImage
import ir.hofa.cloneblogfreerealapi.common.Utils
import ir.hofa.cloneblogfreerealapi.domain.model.blog.BlogItem
import ir.hofa.cloneblogfreerealapi.navigation.Screen
import ir.hofa.cloneblogfreerealapi.presentation.blog.components.BlogListViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailBlog(
    blogItem: BlogItem?,
    navController: NavHostController,
    viewModel: BlogListViewModel = hiltViewModel(),
) {
    val state = viewModel.stateDelNewBlog.value
    val openDialog = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp)
    ) {
        Column(
            Modifier.padding(
                start = 8.dp, end = 8.dp, bottom = 8.dp
            )
        ) {
            Card(
                elevation = 0.dp,
                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
            ) {
                GlideImage(
                    imageModel = blogItem?.image,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                    loading = {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    },
                    // shows an error text message when request failed.
                    failure = {
                        Text(text = "image request failed.")
                    })
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(unbounded = false),
                backgroundColor = Color.Gray,
                elevation = 0.dp,
                shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(unbounded = false)
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    blogItem?.title?.let {
                        Text(
                            text = it,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Monospace,
                            maxLines = 1
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(unbounded = false)
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    val date = blogItem?.createdAt?.let { Utils().convertStringToDate(it) }
                    if (date != null) {
                        Text(
                            text = date,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue,
                            fontFamily = FontFamily.Monospace

                        )
                    }
                }
            }
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight(unbounded = false),
//                contentAlignment = Alignment.TopStart
//            ) {
//
//            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                blogItem?.let {
                    Text(
                        text = it.text,
                        fontSize = 24.sp,
                        fontFamily = FontFamily.Monospace,
                    )
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
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(unbounded = true),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.TopStart
        ) {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, null)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            IconButton(
                onClick = {
                    openDialog.value = true
                })
            {
                Icon(imageVector = Icons.Filled.Delete, null)
            }
        }
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Are you sure wanna delete the blog?")
            },
            text = {
                Spacer(modifier = Modifier.height(5.dp))
            },
            confirmButton = {
                Button(
                    onClick = {
                        openDialog.value = false
                        viewModel.reqDelBlog(blogItem?.id ?: "null")
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Text("Yes delete it", color = Color.Black)
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDialog.value = false },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    elevation = ButtonDefaults.elevation(0.dp)
                ) {
                    Text("No i won't", color = Color.Black)
                }
            }
        )
    }

    when (state.blog?.success) {
        true -> {
            viewModel.getBlog()
            state.blog.success = false
            navController.navigate(Screen.BlogScreen.withArgs(Utils.USERNAME))
        }
        else -> {
            Text(text = state.error)
        }
    }


}