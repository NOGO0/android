package no.gu.no9.data.request

import com.google.gson.annotations.SerializedName
import no.gu.no9.Area

data class SignUpRequest(
    val name: String,
    val age: Int,
    val area: Area,
    val phone: String,
    @SerializedName("account_id") val accountId: String,
    val password: String,
    val skill: List<String>,
)