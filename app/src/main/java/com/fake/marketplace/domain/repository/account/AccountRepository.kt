package com.fake.marketplace.domain.repository.account

import com.fake.marketplace.domain.entities.account.AccountEntity
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    suspend fun signIn(account: AccountEntity)
    suspend fun checkSingIn(): Boolean
    fun getAccount(): Flow<AccountEntity>
    suspend fun logOut()

}