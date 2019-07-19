package com.odaroid.budgee.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.odaroid.budgee.data.AccountsRepository
import com.odaroid.budgee.data.BudgeeDatabase
import com.odaroid.budgee.data.accounts.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class AddAccountViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AccountsRepository

    private val _accountName: MutableLiveData<String> by lazy { MutableLiveData() }

    private val _accountTarget: MutableLiveData<Long> by lazy { MutableLiveData() }

    private val _shouldNavigateBack: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val shouldNavigateBack: LiveData<Boolean>
        get() = _shouldNavigateBack

    init {
        val dataSource = BudgeeDatabase.getInstance(application).accountDao()
        repository = AccountsRepository(dataSource)
    }

    private val _isReadyToSave: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val isReadyToSave: LiveData<Boolean>
        get() = _isReadyToSave

    private val _hasAccountName: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    private val _hasAccountTarget: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun hasAccountName(name: String) {
        _hasAccountName.value = true
        _accountName.value = name
    }

    fun hasNoAccountName() {
        _hasAccountName.value = false
    }

    fun hasAccountTarget(target: Long) {
        _hasAccountTarget.value = true
        _accountTarget.value = target
    }

    fun hasNoAccountTarget() {
        _hasAccountTarget.value = false
    }

    init {
//        Default Setup
        hasNoAccountName()
        hasNoAccountTarget()
        changeButtonState()
    }

    fun changeButtonState() {
        _isReadyToSave.value = (_hasAccountName.value!! && _hasAccountTarget.value!!)
    }

    fun saveAccount() {
        val account = Account(_accountName.value!!, _accountTarget.value!!)
        Timber.d("New Account: $account")
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insert(account)
            }
        }
        _shouldNavigateBack.value = true
    }

    fun isDoneNavigating() {
        _shouldNavigateBack.value = false
    }
}
