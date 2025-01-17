package no.gu.no9.presentation.feature.signup

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.gu.no9.R
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.request.SignUpRequest
import no.gu.no9.presentation.AppNavigationItem
import no.gu.no9.presentation.feature.signup.SignUpViewModel.Companion.age
import no.gu.no9.presentation.feature.signup.SignUpViewModel.Companion.area
import no.gu.no9.presentation.feature.signup.SignUpViewModel.Companion.lst
import no.gu.no9.presentation.feature.signup.SignUpViewModel.Companion.name
import no.gu.no9.presentation.feature.signup.SignUpViewModel.Companion.phone

@Composable
fun SignUpScreen3(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }
    var isVisibleCheck by remember { mutableStateOf(false) }
    val passwordResource : (Boolean) -> Int = {
        if(it) { // true
            R.drawable.ic_visible
        }else{
            R.drawable.ic_invisible
        }
    }
    Column(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.sign_in_logo),
            contentDescription = "logo",
            modifier = modifier
                .padding(
                    top = 120.dp,
                    start = 40.dp
                )
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "아이디")
            }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "비밀번호")
            },
            trailingIcon = {
                IconButton(onClick = {
                    isVisible = !isVisible
                }) {
                    Image(
                        painter = painterResource(id = passwordResource(isVisible)),
                        contentDescription = if (isVisible) "Hide password" else "Show password",
                    )
                }
            },
            visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = passwordCheck,
            onValueChange = { passwordCheck = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "비밀번호 확인") },
            trailingIcon = {
                IconButton(onClick = {
                    isVisibleCheck = !isVisibleCheck
                }) {
                    Image(
                        painter = painterResource(id = passwordResource(isVisibleCheck)),
                        contentDescription = if (isVisibleCheck) "Hide password" else "Show password",
                    )
                }
            },
            visualTransformation = if (isVisibleCheck) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = modifier
                .padding(28.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF3A63CD))
                .clickable {
                    CoroutineScope(Dispatchers.IO).launch {
                        kotlin
                            .runCatching {
                                ApiProvider
                                    .authApi()
                                    .signUp(
                                        signUpRequest = SignUpRequest(
                                            accountId = id,
                                            password = password,
                                            age = age,
                                            area = area,
                                            name = name,
                                            phone = phone,
                                            skill = lst,
                                        )
                                    )
                            }
                            .onSuccess {
                                Log.d("asd", "asd")
                            }
                            .onFailure {
                                println(
                                    SignUpRequest(
                                        accountId = id,
                                        password = password,
                                        age = age,
                                        area = area,
                                        name = name,
                                        phone = phone,
                                        skill = lst,
                                    )
                                )
                            }
                    }
                    navController.navigate(AppNavigationItem.SignIn.route)
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "완료", color = Color.White)
        }
    }
}