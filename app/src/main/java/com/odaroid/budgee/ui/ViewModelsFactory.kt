package com.odaroid.budgee.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.odaroid.budgee.ui.add.AddAccountViewModel
import com.odaroid.budgee.ui.home.AccountsViewModel

class ViewModelsFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            (modelClass.isAssignableFrom(AddAccountViewModel::class.java)) -> AddAccountViewModel(application) as T
            (modelClass.isAssignableFrom(AccountsViewModel::class.java)) -> AccountsViewModel(application) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
