package com.fake.marketplace.domain.repository.account

import com.fake.marketplace.data.source.locale.entities.account.AccountEntity
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun signIn(account: AccountEntity)
    suspend fun getAccount(): Flow<AccountEntity>
    suspend fun logOut()

}