package no.gu.no9.presentation.feature.recruitment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import no.gu.no9.R
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.response.Feed
import no.gu.no9.presentation.AppNavigationItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RecruitmentRequestsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val lst: MutableList<Feed> = remember { mutableStateListOf() }
    LaunchedEffect(Unit) {
        kotlin.runCatching {
            ApiProvider.feedApi().fetchFeeds(
                RecruitmentViewModel.age,
                RecruitmentViewModel.job,
                RecruitmentViewModel.area,
                RecruitmentViewModel.workDay,
                RecruitmentViewModel.startTime,
                RecruitmentViewModel.endTime,
                RecruitmentViewModel.gender,
            )
        }.onSuccess {
            lst.addAll(it.feedList)
            println(lst)
        }.onFailure {
            println(it)
        }
    }
    Box {
        Column(modifier = modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_no9),
                contentDescription = "logo",
                modifier = modifier
                    .padding(
                        top = 50.dp,
                        start = 40.dp
                    )
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "filter",
                    modifier = modifier.clickable {
                        navController.navigate(AppNavigationItem.Filter.route)
                    }
                )
            }
            if (lst.isNotEmpty()) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color(0xFFD9D9D9))
                ) {
                    items(lst) {
                        JobCard(
                            lst = it,
                            modifier = modifier.clickable {
                                println(it.id)
                                RecruitmentViewModel.companyId = it.id
                                navController.navigate(AppNavigationItem.Detail.route)
                            }
                        )
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_chat),
            contentDescription = "chat",
            modifier = modifier.align(Alignment.BottomEnd).clickable {
                navController.navigate(AppNavigationItem.Gpt.route)
            }
        )
    }
}

@Composable
fun JobCard(lst: Feed, modifier: Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (lst.image.isEmpty()) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Gray, shape = RoundedCornerShape(8.dp))
            )
        } else {
            AsyncImage(
                model = lst.image,
                contentDescription = "image",
                modifier = Modifier
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = lst.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = "Location",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = lst.area.name,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "${lst.salary}원/월",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF007BFF)
                )
            }
        }
    }
}