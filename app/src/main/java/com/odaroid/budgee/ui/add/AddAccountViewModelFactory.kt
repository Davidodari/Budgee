package com.odaroid.budgee.ui.add

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddAccountViewModelFactory(
    private val application: Application
) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddAccountViewModel::class.java)) {
            return AddAccountViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}