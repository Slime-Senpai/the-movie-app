package fr.mbds.dtla.idbdata.datasources

import fr.mbds.dtla.idbdata.api.response.CategoryResponse
import fr.mbds.dtla.idbdata.api.response.MovieReponse
import fr.mbds.dtla.idbdata.api.response.TVShowReponse
import fr.mbds.dtla.idbdata.api.response.TokenResponse
import fr.mbds.dtla.idbdata.api.service.MovieService
import fr.mbds.dtla.idbdata.utils.Result
import fr.mbds.dtla.idbdata.utils.parse
import fr.mbds.dtla.idbdata.utils.safeCall

/**
 * Manipule les données de l'application en utilisant un web service
 * Cette classe est interne au module, ne peut être initialisé ou exposé aux autres composants
 * de l'application
 */
internal class OnlineDataSource(private val service: MovieService) {

    /**
     * Récupérer le token du serveur
     * @return [Result<Token>]
     * Si [Result.Succes], tout s'est bien passé
     * Sinon, une erreur est survenue
     */
    suspend fun getToken(): Result<TokenResponse> {
        return safeCall {
            val response = service.getToken()
            response.parse()
        }
    }

    suspend fun getCategories(language: String): Result<List<CategoryResponse.Genre>> {
        return safeCall {
            val response = service.getMoviesCategories(language)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.genres)
                is Result.Error -> result
            }
        }
    }

    suspend fun getMovies(categoryId: Int, language: String): Result<List<MovieReponse.Movie>> {
        return safeCall {
            val response = service.getMoviesByCatId(categoryId.toString(), language)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.movies)
                is Result.Error -> result
            }
        }
    }

    suspend fun getMovie(movieId: Int, language: String): Result<MovieReponse.Movie> {
        return safeCall {
            val response = service.getMovieById(movieId, language)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTVCategories(language: String): Result<List<CategoryResponse.Genre>> {
        return safeCall {
            val response = service.getTVCategories(language)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.genres)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTVShows(categoryId: Int, language: String): Result<List<TVShowReponse.TVShow>> {
        return safeCall {
            val response = service.getTVShowsByCatId(categoryId.toString(), language)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data.tvshows)
                is Result.Error -> result
            }
        }
    }

    suspend fun getTVShow(tvshowId: Int, language: String): Result<TVShowReponse.TVShow> {
        return safeCall {
            val response = service.getTVShowById(tvshowId, language)
            when (val result = response.parse()) {
                is Result.Succes -> Result.Succes(result.data)
                is Result.Error -> result
            }
        }
    }
}

