package no.gu.no9.presentation.feature.recruitment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            .fillMaxSize(0.3f)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "50-60살",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "직종: 서울시 강남구",
            style = TextStyle(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "근무 시간: 월요일 - 금요일",
            style = TextStyle(
                fontSize = 18.sp
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "급여: 월 200만원 - 300만원",
            style = TextStyle(
                fontSize = 18.sp
            )
        )
    }
}
