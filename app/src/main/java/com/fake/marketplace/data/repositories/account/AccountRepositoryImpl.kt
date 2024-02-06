package com.fake.marketplace.data.repositories.account

import com.fake.marketplace.domain.entities.account.AccountEntity
import com.fake.marketplace.domain.repository.account.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(

): AccountRepository {
    override fun signIn(account: AccountEntity) {
        TODO("Not yet implemented")
    }

    override fun getAccount(): Flow<AccountEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun logOut() {
        TODO("Not yet implemented")
    }
}