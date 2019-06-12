package com.odaroid.budgee.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.odaroid.budgee.data.accounts.Account
import com.odaroid.budgee.data.accounts.AccountDao

class AddAccountViewModelFactory(private val database: AccountDao) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddAccountViewModel::class.java)) {
            return AddAccountViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}