package no.gu.no9.data.api

import no.gu.no9.data.request.SmsRequest
import no.gu.no9.data.response.SmsResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SmsApi {

    @POST("/sms/send")
    suspend fun smsSend(
        @Body smsRequest: SmsRequest
    ): SmsResponse
}