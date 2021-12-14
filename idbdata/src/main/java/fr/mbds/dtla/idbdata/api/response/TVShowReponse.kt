package fr.mbds.dtla.idbdata.api.response

import com.google.gson.annotations.SerializedName
import fr.mbds.dtla.idbdata.data.TVShow

internal data class TVShowReponse(
    @SerializedName("results")
    val tvshows: List<TVShow>
) {
    data class TVShow(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("poster_path")
        val poster: String,
        @SerializedName("backdrop_path")
        val backdrop: String,
        @SerializedName("vote_average")
        val vote: String,
        @SerializedName("first_air_date")
        val date: String,
        @SerializedName("overview")
        val description: String
    )
}

internal fun TVShowReponse.TVShow.toTVShow() = TVShow(
    id = id,
    name = name,
    poster = "https://image.tmdb.org/t/p/w200$poster",
    backdrop = "https://image.tmdb.org/t/p/w500$backdrop",
    vote = vote,
    date = date,
    description = description
)