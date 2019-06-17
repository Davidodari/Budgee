package com.odaroid.budgee.data.accounts

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Defines methods for using the Accounts class with Room.
 */
@Dao
interface AccountDao {

    @Query("SELECT * FROM account")
    fun getAll(): LiveData<List<Account>>

    @Insert
    fun insert(vararg accounts: Account)

    @Update
    fun update(account: Account)

    @Query("DELETE FROM account")
    fun clearAllAccounts()

    @Delete
    fun delete(account: Account)
}