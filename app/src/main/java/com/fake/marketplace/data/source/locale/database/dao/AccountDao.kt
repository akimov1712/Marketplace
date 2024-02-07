package com.fake.marketplace.data.source.locale.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fake.marketplace.data.source.locale.database.entities.account.AccountDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM account LIMIT 1")
    fun getAccount(): Flow<AccountDbEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAccount(account: AccountDbEntity)

    @Query("DELETE FROM account")
    suspend fun deleteAccount()

    @Query("SELECT count(*) FROM account")
    suspend fun getCountItems(): Int

}