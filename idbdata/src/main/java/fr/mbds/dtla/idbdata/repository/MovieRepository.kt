package fr.mbds.dtla.idbdata.repository

import fr.mbds.dtla.idbdata.api.response.toCategory
import fr.mbds.dtla.idbdata.api.response.toEntity
import fr.mbds.dtla.idbdata.api.response.toToken
import fr.mbds.dtla.idbdata.data.Category
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

    suspend fun getCategories(): Result<List<Category>> {
        return when(val result = online.getCategories()) {
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

}