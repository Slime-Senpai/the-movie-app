package fr.mbds.dtla.idbdata.repository

import fr.mbds.dtla.idbdata.api.response.*
import fr.mbds.dtla.idbdata.api.response.toCategory
import fr.mbds.dtla.idbdata.api.response.toEntity
import fr.mbds.dtla.idbdata.api.response.toMovie
import fr.mbds.dtla.idbdata.api.response.toToken
import fr.mbds.dtla.idbdata.data.Category
import fr.mbds.dtla.idbdata.data.Movie
import fr.mbds.dtla.idbdata.data.TVShow
import fr.mbds.dtla.idbdata.data.Token
import fr.mbds.dtla.idbdata.datasources.LocalDataSource
import fr.mbds.dtla.idbdata.datasources.OnlineDataSource
import fr.mbds.dtla.idbdata.utils.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    //Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    //Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when(val result = online.getToken()) {
            is Result.Succes -> {
                //save the response in the local database
                local.saveToken(result.data.toEntity())
                //return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(language: String): Result<List<Category>> {
        return when(val result = online.getCategories(language)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovies(categoryId: Int, language: String): Result<List<Movie>> {
        return when(val result = online.getMovies(categoryId, language)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val movies = result.data.map {
                    it.toMovie()
                }
                Result.Succes(movies)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovie(movieId: Int, language: String): Result<Movie> {
        return when(val result = online.getMovie(movieId, language)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val movie = result.data.toMovie()
                Result.Succes(movie)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTVCategories(language: String): Result<List<Category>> {
        return when(val result = online.getTVCategories(language)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTVShows(categoryId: Int, language: String): Result<List<TVShow>> {
        return when(val result = online.getTVShows(categoryId, language)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val tvshows = result.data.map {
                    it.toTVShow()
                }
                Result.Succes(tvshows)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTVShow(tvshowId: Int, language: String): Result<TVShow> {
        return when(val result = online.getTVShow(tvshowId, language)) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val tvshow = result.data.toTVShow()
                Result.Succes(tvshow)
            }
            is Result.Error -> result
        }
    }
}