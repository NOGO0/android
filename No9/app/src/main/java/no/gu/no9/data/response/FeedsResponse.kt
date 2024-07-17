package no.gu.no9.data.response

import com.google.gson.annotations.SerializedName
import no.gu.no9.Area

data class FeedsResponse(
    @SerializedName("feed_list") val feedList: List<Feed>
)

data class Feed(
    val title: String,
    val area: Area,
    val salary: Int,
    val image: String,
)