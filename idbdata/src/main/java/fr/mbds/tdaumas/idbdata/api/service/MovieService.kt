package fr.mbds.tdaumas.idbdata.api.service

import fr.mbds.tdaumas.idbdata.api.response.CategoryResponse
import fr.mbds.tdaumas.idbdata.api.response.TokenResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("/genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>
}