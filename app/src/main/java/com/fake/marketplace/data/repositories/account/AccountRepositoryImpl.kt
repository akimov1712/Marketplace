package com.fake.marketplace.data.repositories.account


import com.fake.marketplace.data.DatabaseEmptyException
import com.fake.marketplace.data.mappers.account.AccountMapper
import com.fake.marketplace.data.source.locale.database.dao.AccountDao
import com.fake.marketplace.domain.entities.account.AccountEntity
import com.fake.marketplace.domain.repository.account.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dao: AccountDao,
): AccountRepository {

    override suspend fun signIn(account: AccountEntity) {
        dao.addAccount(AccountMapper.mapEntityToDb(account))
    }

    override fun getAccount(): Flow<AccountEntity> {
        return dao.getAccount().map {
            it ?: throw DatabaseEmptyException()
            AccountMapper.mapDbToEntity(it)
        }
    }

    override suspend fun logOut() {
        dao.deleteAccount()
    }

    override suspend fun checkSingIn(): Boolean {
        val count = dao.getCountItems()
        return count != 0
    }
}