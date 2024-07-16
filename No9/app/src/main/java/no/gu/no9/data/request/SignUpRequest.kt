package no.gu.no9.data.request

data class SignUpRequest(
    val name: String,
    val age: Int,
    val area: String,
    val phone: String,
    val accountId: String,
    val password: String,
    val skill: List<String>,
)