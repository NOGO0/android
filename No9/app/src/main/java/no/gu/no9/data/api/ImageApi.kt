package no.gu.no9.data.api

import no.gu.no9.data.request.ImageRequest
import no.gu.no9.data.response.ImageResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ImageApi {

    @POST("/image/{feed_id}")
    suspend fun sendImage(
        @Path("feed_id") feedId: Long,
        @Body imageRequest: ImageRequest,
    ): ImageResponse
}