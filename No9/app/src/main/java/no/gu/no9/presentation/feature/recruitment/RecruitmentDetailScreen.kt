package no.gu.no9.presentation.feature.recruitment

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import no.gu.no9.presentation.feature.component.Header

@Composable
fun RecruitmentDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Header(title = "상세", modifier = modifier.padding(start = 28.dp))
        //  AsyncImage(model = "", contentDescription = "image")
        Text(
            text = "1",
            modifier = modifier.padding(28.dp),
        )
        Row(
            modifier = modifier
                .padding(28.dp)
                .fillMaxWidth()
        ) {
            Text(text = "1")
            Text(text = "1")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "1")
        }
        Detail()
        Text(
            text = "salkndksdjnvsadjklnvakjlnvkjsdlnvksjdnsakjdnvskaldnsdkalvnksdlvnklsadvnjasdasdasd",
            modifier = modifier.padding(horizontal = 28.dp),
        )
    }
}

@Composable
fun Detail() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                width = 2.dp,
                color = Color(0xFFc8c8c8),
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color(0xFFc3c3c3))
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "나이: 50~60세",
            style = TextStyle(
                fontSize = 16.sp,
                //fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "직종: ___, __",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "지역: 서울시 강남구",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "근무 요일: 월요일 - 금요일",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "근무 시간: __시 __분 ~ __시 __분",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "성별: 남/여",
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}
