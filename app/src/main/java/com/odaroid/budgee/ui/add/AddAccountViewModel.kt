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

    private val _hasAccountName: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    private val _hasAccountTarget: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun hasAccountName() {
        _hasAccountName.value = true
    }

    fun hasNoAccountName() {
        _hasAccountName.value = false
    }

    fun hasAccountTarget() {
        _hasAccountTarget.value = true
    }

    fun hasNoAccountTarget() {
        _hasAccountTarget.value = false
    }

    init {
//        Default Setup
        hasNoAccountName()
        hasNoAccountTarget()
        addButtonState()
    }

    fun addButtonState() {
        _isReadyToSave.value = (_hasAccountName.value!! && _hasAccountTarget.value!!)
    }

    fun saveAccountInfo(account: Account) {
        Timber.d("New Account: $account")
    }
}