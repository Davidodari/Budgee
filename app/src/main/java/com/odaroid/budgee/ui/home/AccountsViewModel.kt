package com.odaroid.budgee.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.odaroid.budgee.data.AccountsRepository
import com.odaroid.budgee.data.BudgeeDatabase
import com.odaroid.budgee.data.accounts.Account


class AccountsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AccountsRepository
    val accounts: LiveData<List<Account>>

    init {
        val dataSource = BudgeeDatabase.getInstance(application).accountDao()
        repository = AccountsRepository(dataSource)
        accounts = repository.userAccounts
    }
}
