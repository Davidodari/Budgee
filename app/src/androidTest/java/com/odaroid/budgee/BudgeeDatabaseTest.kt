package com.odaroid.budgee

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.odaroid.budgee.data.BudgeeDatabase
import com.odaroid.budgee.data.accounts.Account
import com.odaroid.budgee.data.accounts.AccountDao
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BudgeeDatabaseTest {

    private lateinit var accountDao: AccountDao
    private lateinit var db: BudgeeDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, BudgeeDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        accountDao = db.accountDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun insertAccountTest() {
        val account = Account("Test Account",2000)
        accountDao.insert(account)
        val accountList = accountDao.getAll()
        assertEquals(accountList[0].name, "Test Account")
        assertEquals(accountList[0].target, 2000)
        assertEquals(accountList[0]._id, 1)
    }

}