package no.gu.no9.presentation.feature.gptai

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import no.gu.no9.R
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.request.Gpt
import no.gu.no9.data.request.GptRequest
import no.gu.no9.presentation.feature.component.Header

@Composable
fun GptScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val firstText = "안녕하세요. 제가 사용자님의 복지를 알려드립니다.\n사용자님의 연령을 알려주세요!"
    var lst by remember { mutableStateOf(GptRequest(listOf())) }
    var text by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    if (lst.gpt_list.isNotEmpty()) {
        LaunchedEffect(lst.gpt_list.size) {
            coroutineScope.launch {
                listState.scrollToItem(lst.gpt_list.size - 1)
            }
        }
    }

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
        LazyColumn(
            state = listState,
            modifier = modifier
                .padding(horizontal = 12.dp)
                .fillMaxHeight(0.85f)
        ) {
            item {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_gpt),
                        contentDescription = "gpt",
                    )
                    Box(
                        modifier = modifier
                            .padding(start = 4.dp, end = 60.dp)
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
            }
            // 두 번째 대화부터
            items(lst.gpt_list) { gpt ->
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                ) {
                    if (gpt.role == "user") {
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
                            .padding(
                                end = if (gpt.role == "assistant") 60.dp else 0.dp,
                                start = if (gpt.role == "assistant") 0.dp else 60.dp,
                            )
                            .clip(
                                RoundedCornerShape(
                                    topEnd = if (gpt.role == "assistant") 8.dp else 0.dp,
                                    topStart = if (gpt.role == "assistant") 0.dp else 8.dp,
                                    bottomEnd = 8.dp,
                                    bottomStart = 8.dp
                                )
                            )
                            .background(Color.White)
                    ) {
                        Text(
                            text = gpt.prompt,
                            modifier = modifier.padding(4.dp),
                        )
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .background(Color(0xFFBECCF0))
                .weight(1f)
        )
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = modifier
                .background(Color.White)
                .padding(horizontal = 28.dp, vertical = 18.dp)
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = if (lst.gpt_list.isEmpty()) "나이를 입력하세요" else "원하는 내용을 입력하세요.")
            },
            label = {
                Text(text = "GPT")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // 새로운 질문 추가
                    val userGpt = Gpt(
                        role = "user",
                        prompt = if (lst.gpt_list.isEmpty()) text + "살에 해당하는 취업에 관련된 복지 관련 정보를 알려주세요" else text
                    )
                    lst = lst.copy(gpt_list = lst.gpt_list + userGpt)
                    // GPT 응답 처리
                    CoroutineScope(Dispatchers.IO).launch {
                        kotlin.runCatching {
                            ApiProvider.gptApi().fetchGpt(gptRequest = lst)
                        }.onSuccess { response ->
                            lst = lst.copy(gpt_list = lst.gpt_list + Gpt("assistant", response.answer))
                        }.onFailure { error ->
                            println(error)
                            println(lst)
                        }
                    }
                    // 텍스트 필드 초기화
                    text = ""
                }
            )
        )
    }
}
