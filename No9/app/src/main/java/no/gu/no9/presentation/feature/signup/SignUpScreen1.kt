package no.gu.no9.presentation.feature.signup

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.gu.no9.R
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.request.SmsRequest
import no.gu.no9.presentation.AppNavigationItem
import no.gu.no9.presentation.feature.signup.SignUpViewModel.Companion.name
import no.gu.no9.presentation.feature.signup.SignUpViewModel.Companion.phone

@Composable
fun SignUpScreen1(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var name1 by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var checkNumber by remember { mutableStateOf("") }
    var isCheck by remember { mutableStateOf(false) }
    var checkNum2 by remember { mutableStateOf("") }

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
            value = name1,
            onValueChange = { name1 = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "이름")
            }
        )
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "전화번호")
            },
            trailingIcon = {
                Text(
                    text = "인증하기",
                    color = Color(0xFF3A63CD),
                    modifier = modifier
                        .padding(end = 4.dp)
                        .clickable {
                            isCheck = true
                            CoroutineScope(Dispatchers.IO).launch {
                                runCatching {
                                    ApiProvider.smsSend().smsSend(smsRequest = SmsRequest(phone = phoneNumber))
                                }.onSuccess {
                                    checkNum2 = it.number
                                }
                            }
                        }
                )
            },
        )
        if (isCheck) {
            OutlinedTextField(
                value = checkNumber,
                onValueChange = { checkNumber = it },
                modifier = modifier
                    .padding(
                        horizontal = 28.dp,
                        vertical = 13.dp
                    )
                    .height(60.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(text = "인증번호")
                },
                trailingIcon = {
                    Text(
                        text = "확인",
                        color = Color(0xFF3A63CD),
                        modifier = modifier
                            .padding(end = 4.dp)
                            .clickable {
                                isCheck = true
                                if (checkNumber == checkNum2) {
                                    Log.d("success","good")
                                }
                            }
                    )
                },
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        val context = LocalContext.current
        Box(
            modifier = modifier
                .padding(28.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF3A63CD))
                .clickable {
                    if (checkNumber == checkNum2) {
                        name = name1
                        phone = phoneNumber
                        navController.navigate(AppNavigationItem.SignUp2.route)
                    } else {
                        Toast.makeText(context, "인증 실패",Toast.LENGTH_SHORT).show()
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "다음", color = Color.White)
        }
    }
}