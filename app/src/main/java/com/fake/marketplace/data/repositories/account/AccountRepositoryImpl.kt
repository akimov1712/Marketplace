package com.fake.marketplace.data.repositories.account

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.fake.marketplace.Const.USER_AUTO_PREFS
import com.fake.marketplace.data.ReadErrorSharedPref
import com.fake.marketplace.data.mappers.account.AccountMapper
import com.fake.marketplace.data.source.locale.database.dao.AccountDao
import com.fake.marketplace.domain.entities.account.AccountEntity
import com.fake.marketplace.domain.repository.account.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dao: AccountDao,
): AccountRepository {
    override suspend fun signIn(account: AccountEntity) {
        dao.addAccount(AccountMapper.mapEntityToDb(account))
    }

    override fun getAccount(): Flow<AccountEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun logOut() {
        TODO("Not yet implemented")
    }

    override suspend fun checkSingIn(): Boolean {
        val count = dao.getCountItems()
        return count != 0
    }
}