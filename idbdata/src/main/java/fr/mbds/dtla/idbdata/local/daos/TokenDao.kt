package fr.mbds.dtla.idbdata.local.daos

import androidx.room.*
import fr.mbds.dtla.idbdata.local.entities.TokenEntity

@Dao
internal interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: TokenEntity)

    @Query("SELECT * from idb_token LIMIT 1")
    fun retrieve(): TokenEntity?

    @Query("DELETE FROM idb_token")
    fun delete()
}