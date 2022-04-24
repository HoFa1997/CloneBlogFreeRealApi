package ir.hofa.cloneblogfreerealapi.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ir.hofa.cloneblogfreerealapi.domain.model.login.ReqLoginUserVM
import ir.hofa.cloneblogfreerealapi.presentation.login.components.ReqUserLoginViewModel


@Composable
fun LoginScreen(
    viewModel: ReqUserLoginViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    var email by remember { mutableStateOf(TextFieldValue()) }
    var emailError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }


    LazyColumn {

        item {
            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxSize()
            ) {
                Text(text = "Let's sign you in.", fontSize = 28.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Welcome back.", fontSize = 22.sp)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "You've been missed!", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = email,
                    singleLine = true,
                    onValueChange = {
                        email = it
                        emailError = false
                    },
                    label = { Text(text = "Username") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        if (emailError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = emailError
                )
                if (emailError) {
                    Text(
                        text = "Please enter your username",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = false
                    },
                    singleLine = true,
                    label = { Text(text = "Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (passwordError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = passwordError
                )
                if (passwordError) {
                    Text(
                        text = "Please enter your password",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (state.isLoading) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    Button(
                        onClick = {
                            if (email.text.isEmpty()) {
                                emailError = true
                            }
                            if (password.text.isEmpty()) {
                                passwordError = true
                            }
                            if (emailError || passwordError) return@Button
                            isLoading = true

                            viewModel.reqUserLogin(
                                ReqLoginUserVM(
                                    email = email.text,
                                    password = password.text
                                )
                            )
                        },
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors()
                    ) {
                        Text(
                            text = "Login", fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    if (state.token?.success == true)
                        Text(text = "Welcome Hossein")
                }
            }
        }
    }
}