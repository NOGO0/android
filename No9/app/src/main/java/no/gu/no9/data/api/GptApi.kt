package no.gu.no9.data.api

import no.gu.no9.data.request.GptRequest
import no.gu.no9.data.response.GptResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GptApi {

    @POST("/gpt")
    suspend fun fetchGpt(
        @Body gptRequest: GptRequest
    ): GptResponse
}