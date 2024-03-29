package com.fake.marketplace.domain.useCases.account

import com.fake.marketplace.domain.repository.account.AccountRepository
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    operator fun invoke() = repository.getAccount()

}