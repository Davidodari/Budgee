package com.odaroid.budgee.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.odaroid.budgee.data.accounts.Account
import com.odaroid.budgee.data.accounts.AccountDao

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class BudgeeDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: BudgeeDatabase? = null

        fun getInstance(context: Context): BudgeeDatabase {
            synchronized(this) {
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(
                            context.applicationContext,
                            BudgeeDatabase::class.java,
                            "budgee_database"
                        )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }
}