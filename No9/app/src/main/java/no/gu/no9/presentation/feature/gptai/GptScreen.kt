package no.gu.no9.presentation.feature.gptai

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.gu.no9.Area
import no.gu.no9.R
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.request.Gpt
import no.gu.no9.data.request.GptRequest
import no.gu.no9.presentation.feature.component.Header
import no.gu.no9.presentation.feature.recruitment.RecruitmentViewModel

@Composable
fun GptScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val firstText = "안녕하세요. 제가 사용자님의 복지를 알려드립니다.\n사용자님의 연령을 알려주세요!"
    var lst: List<Gpt>? by remember { mutableStateOf(null) }
    lst = listOf(Gpt("assistant", "asdasd"), Gpt("user", "kacmkl"))
    var text by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFBECCF0))
    ) {
        Header(
            title = "GPT",
            modifier = modifier
                .padding(start = 28.dp, top = 8.dp)
                .clickable { navController.popBackStack() },
        )
        LazyColumn(modifier = modifier.padding(horizontal = 12.dp)) {
            item {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_gpt),
                        contentDescription = "gpt",
                    )
                    Box(
                        modifier = modifier
                            .padding(
                                start = 4.dp,
                                end = 60.dp
                            )
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 8.dp,
                                    bottomEnd = 8.dp,
                                    bottomStart = 8.dp
                                )
                            )
                            .background(Color.White)
                    ) {
                        Text(
                            text = firstText,
                            modifier = modifier.padding(4.dp)
                        )
                    }
                }
                Row(Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = modifier
                            .padding(top = 24.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 8.dp,
                                    bottomEnd = 8.dp,
                                    bottomStart = 8.dp
                                )
                            )
                            .background(Color.White)
                    ) {
                        Text(
                            text = "18세",
                            modifier = modifier.padding(4.dp)
                        )
                    }
                }
            }
            //이건 두번째 대화부터
            if (!lst.isNullOrEmpty()) {
                items(lst!!) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        if (it.role == "user") {
                            Spacer(modifier = Modifier.weight(1f))
                        } else {
                            Image(
                                painter = painterResource(id = R.drawable.ic_gpt),
                                contentDescription = "gpt",
                                modifier = modifier.padding(end = 4.dp)
                            )
                        }
                        Box(
                            modifier = modifier
                                .padding(end = if (it.role == "assistant") 60.dp else 0.dp)
                                .clip(
                                    RoundedCornerShape(
                                        topEnd = if (it.role == "assistant") 8.dp else 0.dp,
                                        topStart = if (it.role == "assistant") 0.dp else 8.dp,
                                        bottomEnd = 8.dp,
                                        bottomStart = 8.dp
                                    )
                                )
                                .background(Color.White)
                        ) {
                            Text(
                                text = it.prompt,
                                modifier = modifier.padding(4.dp),
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = modifier
                .background(Color.White)
                .padding(
                    horizontal = 28.dp,
                    vertical = 18.dp
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = if (lst.isNullOrEmpty()) "나이를 입력하세요" else "원하는 내용을 입력하세요.")
            },
            label = {
                Text(text = "GPT")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    CoroutineScope(Dispatchers.IO).launch {
                        kotlin.runCatching {
                            ApiProvider.gptApi().fetchGpt(gptRequest = lst)
                        }.onSuccess {
                            lst = if (lst.isNullOrEmpty()) {
                                listOf(Gpt(role = "assistant", prompt = it.answer))
                            } else {
                                lst!! + Gpt(role = "assistant", prompt = it.answer)
                            }
                        }
                    }
                }
            )
        )
    }
}