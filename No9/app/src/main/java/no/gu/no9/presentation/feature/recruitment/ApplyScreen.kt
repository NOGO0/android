package no.gu.no9.presentation.feature.recruitment

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.request.ApplyRequest
import no.gu.no9.presentation.AppNavigationItem
import no.gu.no9.presentation.feature.component.Header

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ApplyScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var name by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var introduce by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize()) {
        Header(
            title = "지원",
            modifier = modifier
                .padding(start = 28.dp)
                .clickable { navController.popBackStack() },
        )
        Text(
            text = "이름", fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 28.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "이름을 입력하세요.")
            },
            label = {
                Text(text = "이름")
            }
        )
        Text(
            text = "지역", fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 28.dp)
        )
        OutlinedTextField(
            value = area,
            onValueChange = { area = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "살고있는 지역을 입력하세요.")
            },
            label = {
                Text(text = "지역")
            }
        )
        Text(
            text = "연령", fontWeight = FontWeight.Bold, modifier = modifier.padding(start = 28.dp)
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "현재 나이를 입력하세요.")
            },
            label = {
                Text(text = "연령")
            }
        )
        Text(
            text = "연략처", fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 28.dp)
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "ex) 01012345678")
            },
            label = {
                Text(text = "연락처")
            }
        )
        Text(
            text = "자기소개", fontWeight = FontWeight.Bold,
            modifier = modifier.padding(start = 28.dp)
        )
        OutlinedTextField(
            value = introduce,
            onValueChange = { introduce = it },
            modifier = modifier
                .padding(
                    horizontal = 28.dp,
                    vertical = 13.dp
                )
                .height(160.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "자기소개를 해주세요. \nex) 경력, 자격증 등")
            },
            label = {
                Text(text = "자기소개")
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        val context = LocalContext.current
        Box(
            modifier = modifier
                .padding(bottom = 40.dp, start = 28.dp, end = 28.dp)
                .fillMaxWidth()
                .height(55.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF3A63CD))
                .clickable {
                    CoroutineScope(Dispatchers.IO).launch {
                        runCatching {
                            ApiProvider.applyApi().apply(
                                feedId = RecruitmentViewModel.companyId,
                                applyRequest = ApplyRequest(about_me = introduce),
                            )
                        }.onSuccess {
                            withContext(Dispatchers.Main) {
                                navController.navigate(AppNavigationItem.RecruitmentRequests.route)
                            }
                        }.onFailure {
                            Toast.makeText(context,"지원에 실패 했어요.",Toast.LENGTH_SHORT).show()
                        }
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "지원하기", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}