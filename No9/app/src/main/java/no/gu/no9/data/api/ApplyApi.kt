package no.gu.no9.data.api

import no.gu.no9.data.request.ApplyRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApplyApi {

    @POST("/applys/{feed_id}")
    suspend fun apply(
        @Path("feed_id") feedId: Long,
        @Body applyRequest: ApplyRequest,
    )
}