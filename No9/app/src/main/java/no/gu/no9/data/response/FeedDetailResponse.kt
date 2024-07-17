package no.gu.no9.data.response

import no.gu.no9.Area
import no.gu.no9.Gender
import no.gu.no9.Job

data class FeedDetailResponse(
    val id: Int,
    val image: String,
    val title: String,
    val money: Int,
    val age: Int,
    val job: Job,
    val area: Area,
    val day: String,
    val start_time: String,
    val end_time: String,
    val gender: Gender,
    val content: String,
    val phone: String,
    val create_date: String,
)

