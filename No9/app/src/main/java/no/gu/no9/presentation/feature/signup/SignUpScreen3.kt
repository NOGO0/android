package no.gu.no9.presentation.feature.signup

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import no.gu.no9.R
import no.gu.no9.presentation.AppNavigationItem

@Composable
fun SignUpScreen3(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCheck by remember { mutableStateOf("") }
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
            placeholder = {
                Text(text = "비밀번호 확인")
            },
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
                    navController.navigate(AppNavigationItem.SignIn.route)
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "완료", color = Color.White)
        }
    }
}