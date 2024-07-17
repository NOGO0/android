package no.gu.no9.data.request

data class GptRequest(
    val gpt_list: List<Gpt>,
)
//role -> gpt면 assistant, 사용자면 user
data class Gpt(
    val role: String,
    val prompt: String,
)