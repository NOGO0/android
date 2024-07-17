package no.gu.no9.presentation.feature.recruitment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import no.gu.no9.Area
import no.gu.no9.Gender
import no.gu.no9.Job
import no.gu.no9.WorkDay
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class RecruitmentViewModel: ViewModel() {
    companion object {
        var age: Int? = null
        var area: Area? = null
        var gender: Gender? = null
        var startTime: LocalTime? = null
        var endTime: LocalTime? = null
        var workDay: WorkDay? = null
        var job: Job? = null
        var companyId: Long = 0
    }
}