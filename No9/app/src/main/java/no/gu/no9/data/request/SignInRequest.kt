package no.gu.no9.data.request

import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("account_id") val accountId: String,
    val password: String,
)
