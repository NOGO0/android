package no.gu.no9.presentation.feature.signin

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import no.gu.no9.R
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.request.SignInRequest
import no.gu.no9.presentation.AppNavigationItem

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    signInViewModel: SignInViewModel = viewModel(),
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isVisible by remember { mutableStateOf(false) }
    val passwordResource : (Boolean) -> Int = {
        if(it) { // true
            R.drawable.ic_visible
        }else{
            R.drawable.ic_invisible
        }
    }

    val context = LocalContext.current
    var token by remember { mutableStateOf("") }
    val sharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)


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
                Text(text = "아이디를 입력하세요")
            },
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
            placeholder = { Text(text = "비밀번호를 입력하세요") },
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
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = buildAnnotatedString {
                append("아직 회원가입을 하지 않으셨나요? ")
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("회원가입하기")
                }
            },
            fontSize = 16.sp,
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp)
                .clickable {
                    navController.navigate(AppNavigationItem.SignUp1.route)
                },
            textAlign = TextAlign.Center,
        )
        Box(
            modifier = modifier
                .padding(start = 28.dp, end = 28.dp, bottom = 28.dp)
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
                                    .signIn(SignInRequest(accountId = id, password = password))
                            }
                            .onSuccess {
                                token = it.accessToken
                                println(it)
                                sharedPreferences
                                    .edit()
                                    .putString("token", token)
                                    .apply()
                                withContext(Dispatchers.Main) {
                                    navController.navigate(AppNavigationItem.RecruitmentRequests.route)
                                }
                            }
                            .onFailure {
                                println(it)
                            }
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "로그인", color = Color.White)
        }
    }
}