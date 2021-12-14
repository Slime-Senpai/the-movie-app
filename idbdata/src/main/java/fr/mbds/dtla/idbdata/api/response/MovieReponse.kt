package fr.mbds.dtla.idbdata.api.response

import com.google.gson.annotations.SerializedName
import fr.mbds.dtla.idbdata.data.Movie

internal data class MovieReponse(
    @SerializedName("results")
    val movies: List<Movie>
) {
    data class Movie(
        @SerializedName("id")
        val id: Int,
        @SerializedName("title")
        val name: String,
        @SerializedName("poster_path")
        val poster: String,
        @SerializedName("backdrop_path")
        val backdrop: String,
        @SerializedName("vote_average")
        val vote: String,
        @SerializedName("release_date")
        val date: String,
        @SerializedName("overview")
        val description: String
    )
}

internal fun MovieReponse.Movie.toMovie() = Movie(
    id = id,
    name = name,
    poster = "https://image.tmdb.org/t/p/w200$poster",
    backdrop = "https://image.tmdb.org/t/p/w500$backdrop",
    vote = vote,
    date = date,
    description = description
)