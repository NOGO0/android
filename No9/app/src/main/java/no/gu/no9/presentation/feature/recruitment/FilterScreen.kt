package no.gu.no9.presentation.feature.recruitment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import no.gu.no9.Area
import no.gu.no9.Gender
import no.gu.no9.Job
import no.gu.no9.WorkDay
import no.gu.no9.presentation.feature.component.Header
import no.gu.no9.presentation.feature.component.TimePickerExample
import java.time.LocalTime

fun checkAge(age: Int): Int {
    return age - age % (age / 10)
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    var age by remember { mutableStateOf("") }
    var area by remember { mutableStateOf("") }
    val lst = listOf("보육", "주방", "경비", "IT", "서빙", "사무", "배달", "기타")
    val areas: MutableList<String> = remember { mutableStateListOf() }
    var malecheck by remember { mutableStateOf(false) }
    var femalecheck by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Header(
            title = "필터",
            modifier = modifier
                .padding(start = 28.dp)
                .clickable { navController.popBackStack() })
        Text(
            text = "연령",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = modifier.padding(start = 28.dp)
        )
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            modifier = modifier
                .padding(
                    vertical = 13.dp,
                    horizontal = 28.dp,
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "현재 나이 입력")
            }
        )
        Text(
            text = "직무",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = modifier.padding(horizontal = 28.dp, vertical = 10.dp)
        )
        FlowRow(
            modifier = Modifier
                .padding(horizontal = 28.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            lst.forEach {
                var check by remember { mutableStateOf(false) }
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color(0xFFEEEEEE))
                        .clickable {
                            check = !check
                            RecruitmentViewModel.job = when (it) {
                                "보육" -> Job.CARE
                                "주방" -> Job.COOK
                                "경비" -> Job.GUARD
                                "IT" -> Job.DEVELOPER
                                "서빙" -> Job.SERVING
                                "사무" -> Job.DOCUMENT
                                "배달" -> Job.DELIVERY
                                else -> Job.SALES
                            }
                        }
                        .border(
                            width = if (check) 2.dp else 0.dp,
                            color = if (check) Color(0xFF3A63CD) else Color(0x00FFFFFF),
                            shape = RoundedCornerShape(100.dp),
                        )
                ) {
                    Text(
                        text = it,
                        modifier = modifier.padding(6.dp),
                        color = if (check) Color(0xFF3A63CD) else Color.Black,
                    )
                }
            }
        }
        Text(
            text = "근무 지역",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = modifier.padding(start = 28.dp)
        )
        OutlinedTextField(
            value = area,
            onValueChange = { area = it },
            modifier = modifier
                .padding(
                    vertical = 13.dp,
                    horizontal = 28.dp,
                )
                .height(60.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "지역명을 입력하세요.")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    areas.add(area)
                    RecruitmentViewModel.area = when (area) {
                        "서울" -> Area.SEOUL
                        "부산" -> Area.BUSAN
                        "인천" -> Area.INCHEON
                        "대전" -> Area.DAEJEON
                        else -> Area.GWANGJU
                    }
                    area = ""
                }
            )
        )
        Row {
            areas.forEach {
                Box(
                    modifier = modifier
                        .padding(start = 28.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 2.dp,
                            color = Color(0xFF86A8FF),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(Color(0xFFBECCF0))
                ) {
                    Row {
                        Text(
                            text = it,
                            color = Color(0xFF3A63CD),
                            modifier = modifier.padding(start = 8.dp, top = 4.dp, bottom = 4.dp),
                        )
                        Text(
                            text = "x",
                            modifier = modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .clickable { areas.remove(it) },
                            color = Color(0xFF3A63CD)
                        )
                    }
                }
            }
        }
        Text(
            text = "근무 요일",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = modifier.padding(horizontal = 28.dp, vertical = 10.dp)
        )
        FlowRow(
            modifier = Modifier
                .padding(horizontal = 28.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            listOf("월", "화", "수", "목", "금", "토", "일").forEach {
                var check by remember { mutableStateOf(false) }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color(0xFFEEEEEE))
                        .border(
                            width = if (check) 2.dp else 0.dp,
                            color = if (check) Color(0xFF3A63CD) else Color(0x00FFFFFF),
                            shape = RoundedCornerShape(100.dp),
                        )
                        .padding(vertical = 6.dp, horizontal = 10.dp)
                        .clickable {
                            check = !check
                            RecruitmentViewModel.workDay = when (it) {
                                "월" -> WorkDay.MON
                                "화" -> WorkDay.TUE
                                "수" -> WorkDay.WED
                                "목" -> WorkDay.THU
                                "금" -> WorkDay.FRI
                                "토" -> WorkDay.SAT
                                else -> WorkDay.SUN
                            }
                        }
                ) {
                    Text(
                        text = it,
                        color = if (check) Color(0xFF3A63CD) else Color.Black,
                    )
                }
            }
        }
        Text(
            text = "근무 시간",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = modifier.padding(horizontal = 28.dp, vertical = 10.dp)
        )
        TimePickerExample(
            fetchStartTime = {
                RecruitmentViewModel.startTime =
                    if (it == "23:30") null else LocalTime.parse("$it:00")
            },
            fetchEndTime = {
                RecruitmentViewModel.endTime =
                    if (it == "23:30") null else LocalTime.parse("$it:00")
            },
        )
        Text(
            text = "성별",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = modifier.padding(horizontal = 28.dp, vertical = 10.dp)
        )
        Row {
            Box(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(0.5f)
                    .height(60.dp)
                    .background(Color(0xFFF7F7F7))
                    .border(
                        width = if (malecheck) 2.dp else 0.dp,
                        color = if (malecheck) Color(0xFF3A63CD) else Color(0xFFF7F7F7),
                        shape = RoundedCornerShape(8.dp),
                    )
                    .clip(RoundedCornerShape(7.dp))
                    .align(Alignment.CenterVertically)
                    .clickable {
                        malecheck = !malecheck
                        if (malecheck) femalecheck = false
                        RecruitmentViewModel.gender = Gender.MALE
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center), text = "남",
                    color = if (malecheck) Color(0xFF3A63CD) else Color.Black
                )
            }
            Box(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color(0xFFF7F7F7))
                    .border(
                        width = if (femalecheck) 2.dp else 0.dp,
                        color = if (femalecheck) Color(0xFF3A63CD) else Color(0x00FFFFFF),
                        shape = RoundedCornerShape(8.dp),
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        femalecheck = !femalecheck
                        if (femalecheck) malecheck = false
                        RecruitmentViewModel.gender = Gender.FEMALE
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center), text = "여",
                    color = if (femalecheck) Color(0xFF3A63CD) else Color.Black,
                )
            }
        }
        Box(
            modifier = modifier
                .padding(top = 60.dp, start = 28.dp, end = 28.dp)
                .fillMaxWidth()
                .height(55.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF3A63CD))
                .clickable {
                    RecruitmentViewModel.age = if (age != "") checkAge(age.toInt()) else 0
                    navController.popBackStack()
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "적용하기", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}