package no.gu.no9.data.api

import no.gu.no9.Area
import no.gu.no9.Gender
import no.gu.no9.Job
import no.gu.no9.WorkDay
import no.gu.no9.data.response.FeedsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalTime

interface FeedApi {

    @GET("/feeds/list?age=&job=&area=&workDay=&startTime=&endTime=&gender=")
    suspend fun fetchFeeds(
        @Query("age") age: Int?,
        @Query("job") job: Job?,
        @Query("area") area: Area?,
        @Query("workDay") workDay: WorkDay?,
        @Query("startTime") startTime: LocalTime?,
        @Query("endTime") endTime: LocalTime?,
        @Query("gender") gender: Gender?,
    ): FeedsResponse
}