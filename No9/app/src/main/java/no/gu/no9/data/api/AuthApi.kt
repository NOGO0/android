package no.gu.no9.data.api

import no.gu.no9.data.request.SignInRequest
import no.gu.no9.data.request.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("users/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    )

    @POST("users/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    )
}
