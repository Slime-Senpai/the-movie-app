package fr.mbds.dtla.idbdata.api.service

import fr.mbds.dtla.idbdata.api.response.CategoryResponse
import fr.mbds.dtla.idbdata.api.response.MovieReponse
import fr.mbds.dtla.idbdata.api.response.TVShowReponse
import fr.mbds.dtla.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getMoviesCategories(
        @Query("language") language: String
    ): Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getMoviesByCatId(
        @Query("with_genres") genre: String,
        @Query("language") language: String
    ): Response<MovieReponse>

    @GET("movie/{id}")
    suspend fun getMovieById(
        @Path("id") id: Int,
        @Query("language") language: String
    ): Response<MovieReponse.Movie>

    @GET("genre/tv/list")
    suspend fun getTVCategories(
        @Query("language") language: String
    ): Response<CategoryResponse>

    @GET("discover/tv")
    suspend fun getTVShowsByCatId(
        @Query("with_genres") genre: String,
        @Query("language") language: String
    ): Response<TVShowReponse>

    @GET("tv/{id}")
    suspend fun getTVShowById(
        @Path("id") id: Int,
        @Query("language") language: String
    ): Response<TVShowReponse.TVShow>
}