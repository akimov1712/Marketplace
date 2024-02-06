package com.fake.marketplace.domain.useCases.account

import com.fake.marketplace.domain.repository.account.AccountRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    suspend operator fun invoke() = repository.logOut()

}