package com.odaroid.budgee.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odaroid.budgee.data.accounts.Account
import timber.log.Timber

class AddAccountViewModel : ViewModel() {

    private val _isReadyToSave: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isReadyToSave: LiveData<Boolean>
        get() = _isReadyToSave

    init {
        _isReadyToSave.value = false
    }

    fun enableSaveButton() {
        _isReadyToSave.value = true
    }

    fun disableSaveButton() {
        _isReadyToSave.value = false
    }

    fun saveAccountInfo(account: Account) {
        Timber.d("New Acc: $account")
    }
}