package com.odaroid.budgee.data

import androidx.lifecycle.LiveData
import com.odaroid.budgee.data.accounts.Account
import com.odaroid.budgee.data.accounts.AccountDao

class AccountsRepository(private val accountDao: AccountDao) {

    val userAccounts: LiveData<List<Account>> = accountDao.getAll()

    fun insert(account: Account) {
        accountDao.insert(account)
    }

}