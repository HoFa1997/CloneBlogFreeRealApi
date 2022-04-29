package ir.hofa.cloneblogfreerealapi.presentation.blog

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ir.hofa.cloneblogfreerealapi.R
import ir.hofa.cloneblogfreerealapi.common.BitmapToFile
import ir.hofa.cloneblogfreerealapi.domain.model.blog.newblog.NewBlog
import ir.hofa.cloneblogfreerealapi.navigation.Screen
import ir.hofa.cloneblogfreerealapi.presentation.blog.components.BlogListViewModel
import ir.hofa.cloneblogfreerealapi.presentation.ui.theme.secondaryColor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


@Composable
fun NewBlogScreen(
    navHostController: NavHostController,
    viewModel: BlogListViewModel = hiltViewModel(),
) {
    val state = viewModel.stateNewBlog.value

    val context = LocalContext.current


    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }
    val bitmapImage = bitmap.value
    val launcherGallery =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent())
        { uri: Uri? -> imageUri = uri }
    imageUri?.let {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images
                .Media.getBitmap(context.contentResolver, it)

        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver, it)
            bitmap.value = ImageDecoder.decodeBitmap(source)
        }
    }


    var title by remember { mutableStateOf(TextFieldValue()) }
    var titleError by remember { mutableStateOf(false) }
    var tags by remember { mutableStateOf(TextFieldValue()) }
    var tagsError by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(TextFieldValue()) }
    var textError by remember { mutableStateOf(false) }


    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Toast.makeText(context, "Permission Accepted", Toast.LENGTH_SHORT).show()
            launcherGallery.launch("image/*")
        } else {
            // Permission Denied: Do something
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }


    Column(
        Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = title,
            singleLine = true,
            onValueChange = {
                title = it
                titleError = false
            },
            label = { Text(text = "Title") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (titleError) {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = "error",
                        tint = Color.Red
                    )
                }
            },
            isError = titleError
        )
        if (titleError) {
            Text(
                text = "Please enter blog title",
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = tags,
            singleLine = true,
            onValueChange = {
                tags = it
                tagsError = false
            },
            label = { Text(text = "Tags") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (tagsError) {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = "error",
                        tint = Color.Red
                    )
                }
            },
            isError = tagsError
        )
        if (tagsError) {
            Text(
                text = "Please enter blog tags",
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = text,
            singleLine = true,
            onValueChange = {
                text = it
                textError = false
            },
            label = { Text(text = "Text") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (textError) {
                    Icon(
                        imageVector = Icons.Filled.Warning,
                        contentDescription = "error",
                        tint = Color.Red
                    )
                }
            },
            isError = textError
        )
        if (textError) {
            Text(
                text = "Please enter blog tags",
                color = Color.Red,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            border = BorderStroke(5.dp, color = Color.Black)
        ) {
            if (bitmap.value == null) {
                Image(
                    painterResource(R.drawable.phimage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    contentScale = ContentScale.Crop,
                    bitmap = bitmap.value!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(unbounded = false)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                // Check permission
                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) -> {
                        // Some works that require permission
                        launcherGallery.launch("image/*")
                    }
                    else -> {
                        // Asking for permission
                        launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    }
                }

            }
        ) {
            Text(text = "Select Photo")
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(secondaryColor),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    val bitmapUri = BitmapToFile(context).saveImageInInternalStorage(bitmapImage!!)
                    val imageBitmap = Uri.parse(bitmapUri)

                    val requestFile: RequestBody =
                        File(bitmapUri).asRequestBody("multipart/form-data".toMediaTypeOrNull())
                    val blogImage: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "image",
                        "$imageBitmap",
                        requestFile
                    )

                    // Log.e("convert", "$convert", )
                    Log.e("blogimage", "$blogImage")
                    Log.e("requestfile", "$requestFile")

                    val requestTitle: RequestBody =
                        title.text.toRequestBody("multipart/form-data".toMediaTypeOrNull())

                    val requestTags: RequestBody =
                        tags.text.toRequestBody("multipart/form-data".toMediaTypeOrNull())

                    val requestText: RequestBody =
                        text.text.toRequestBody("multipart/form-data".toMediaTypeOrNull())

                    val data = NewBlog(blogImage!!, requestTitle, requestTags, requestText)
                    Log.e("LOG", "$data ")
                    viewModel.reqNewBlog(data)

                }) {
                Text(text = "Save Blog")
            }

            when (state.blog?.success) {
                true -> {
                    navHostController.navigate(Screen.HomeScreen.route)
                    state.blog.success = false
                    viewModel.getBlog()
                }
                else -> {
                    Text(text = state.error)
                }
            }
        }
    }


}