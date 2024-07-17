package no.gu.no9.presentation.feature.recruitment

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import no.gu.no9.Area
import no.gu.no9.Gender
import no.gu.no9.Job
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.request.ApplyRequest
import no.gu.no9.data.response.FeedDetailResponse
import no.gu.no9.presentation.AppNavigationItem
import no.gu.no9.presentation.feature.component.Header

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecruitmentDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val context = LocalContext.current
    var detail: FeedDetailResponse by remember {
        mutableStateOf(
            FeedDetailResponse(
                0, "", "", 0, 0, Job.SALES, Area.SEOUL, "", "", "" +
                        "", Gender.MALE, "", "", ""
            )
        )
    }
    LaunchedEffect(Unit) {
        runCatching {
            ApiProvider.feedApi().fetchDetail(
                feedId = RecruitmentViewModel.companyId
            )
        }.onSuccess {
            detail = it
            println(it)
        }.onFailure {
            println(it)
        }
    }
    if (detail.id != 0) {
        Column(modifier = modifier.fillMaxSize()) {
            Header(
                title = "상세",
                modifier = modifier
                    .padding(start = 28.dp)
                    .clickable { navController.popBackStack() },
            )
            AsyncImage(
                model = detail.image,
                contentDescription = "image",
                modifier = modifier
                    .padding(horizontal = 28.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .size(100.dp),
            )
            Text(
                text = detail.title,
                modifier = modifier.padding(horizontal = 28.dp, vertical = 12.dp),
                fontSize = 26.sp,
            )
            Row(
                modifier = modifier
                    .padding(horizontal = 28.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = detail.money.toString() + "원/월",
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = detail.create_date,
                    textAlign = TextAlign.Center,
                )
            }
            Detail(detail = detail)
            Text(
                text = detail.content,
                modifier = modifier.padding(horizontal = 28.dp),
            )
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Box(
                    modifier = modifier
                        .padding(bottom = 40.dp, start = 28.dp)
                        .weight(1f)
                        .height(55.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFEEEEEE))
                        .clickable {
                            val phoneNumber = "tel:${detail.phone}"
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse(phoneNumber)
                            }
                            context.startActivity(intent)
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Default.Call, contentDescription = "phone")
                }
                Box(
                    modifier = modifier
                        .padding(bottom = 40.dp, start = 14.dp, end = 28.dp)
                        .weight(2f)
                        .height(55.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF3A63CD))
                        .clickable { navController.navigate(AppNavigationItem.Apply.route) },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "지원하기",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun Detail(detail: FeedDetailResponse?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Color(0xFFF7F7F7))
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "나이: ${detail!!.age} ~ ${detail.age + 10}세",
            style = TextStyle(
                fontSize = 16.sp,
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "직종: ${detail.job}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "지역: ${detail.area}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "근무 요일: ${detail.day}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "근무 시간: ${detail.start_time} ~ ${detail.end_time}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "성별: ${detail.gender.name}",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}
