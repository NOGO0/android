package no.gu.no9.data.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    val name: String,
    val age: Int,
    val area: String,
    val phone: String,
    @SerializedName("account_id") val accountId: String,
    val password: String,
    val skill: List<String>,
)