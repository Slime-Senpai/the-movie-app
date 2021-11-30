package fr.mbds.tdaumas.idbdata.datasources

import fr.mbds.tdaumas.idbdata.local.daos.TokenDao
import fr.mbds.tdaumas.idbdata.local.entities.TokenEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class LocalDataSource: KoinComponent {
    private val tokenDao: TokenDao by inject()

    suspend fun getToken() = withContext(Dispatchers.IO) {
        tokenDao.retrieve()
    }

    suspend fun saveToken(entity: TokenEntity) = withContext(Dispatchers.IO) {
        tokenDao.insert(entity)
    }
}