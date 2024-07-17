package no.gu.no9.presentation.feature.component

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun TimePickerExample(
    fetchEndTime: (String) -> Unit,
    fetchStartTime: (String) -> Unit,
) {
    var startTime by remember { mutableStateOf("시작시간") }
    var endTime by remember { mutableStateOf("종료시간") }
    var startExpanded by remember { mutableStateOf(false) }
    var endExpanded by remember { mutableStateOf(false) }
    val times = (0..23).flatMap { hour ->
        listOf("%02d:00".format(hour), "%02d:30".format(hour))
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            OutlinedTextField(
                value = startTime,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                trailingIcon = {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "drop-down",
                        modifier = Modifier.clickable { startExpanded = true }
                    )
                },
                placeholder = { Text(text = "시작시간") }
            )
            DropdownMenu(
                expanded = startExpanded,
                onDismissRequest = { startExpanded = false }
            ) {
                times.forEach { time ->
                    DropdownMenuItem(onClick = {
                        startTime = time
                        startExpanded = false
                    },
                        text = {
                            Text(text = time)
                            fetchStartTime(time)
                            Log.d("it", LocalTime.parse("$time:00").toString())
                        })
                }
            }
        }

        Text(text = " ~ ")

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            OutlinedTextField(
                value = endTime,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                trailingIcon = {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "drop-down",
                        modifier = Modifier.clickable { endExpanded = true },
                    )
                },
                placeholder = { Text(text = "종료시간") }
            )
            DropdownMenu(
                expanded = endExpanded,
                onDismissRequest = { endExpanded = false }
            ) {
                times.forEach { time ->
                    DropdownMenuItem(onClick = {
                        endTime = time
                        endExpanded = false
                    }, text = {
                        Text(text = time)
                        fetchEndTime(time)
                    })
                }
            }
        }
    }
}