package no.gu.no9.presentation.feature.signup

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import no.gu.no9.presentation.AppNavigationItem
import no.gu.no9.presentation.feature.signup.SignUpViewModel.Companion.lst

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SignUpScreen2(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val jobs = listOf("개발", "기획", "디자인", "마케팅", "스타트업", "QA", "PM", "기타")
    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "본인의 직종을\n선택해 주세요",
            fontSize = 38.sp,
            lineHeight = 40.sp,
            modifier = modifier.padding(start = 24.dp, top = 64.dp),
        )
        FlowRow(
            maxItemsInEachRow = 2,
            modifier = modifier.fillMaxWidth()
        ) {
            jobs.forEach {
                Box(
                    modifier = modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .weight(1f)
                        .height(140.dp)
                        .clickable {
                            lst = listOf(it)
                            navController.navigate(AppNavigationItem.SignUp4.route)
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = it,
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}