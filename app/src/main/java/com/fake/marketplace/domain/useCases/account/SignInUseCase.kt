package com.fake.marketplace.domain.useCases.account

import com.fake.marketplace.domain.entities.account.AccountEntity
import com.fake.marketplace.domain.repository.account.AccountRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    operator fun invoke(account: AccountEntity){
        repository.signIn(account)
    }

}