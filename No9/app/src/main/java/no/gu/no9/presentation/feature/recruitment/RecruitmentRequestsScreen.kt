package no.gu.no9.presentation.feature.recruitment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import no.gu.no9.R

@Composable
fun RecruitmentRequestsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_no9),
            contentDescription = "logo",
            modifier = modifier
                .padding(
                    top = 120.dp,
                    start = 40.dp
                )
        )
    }
}