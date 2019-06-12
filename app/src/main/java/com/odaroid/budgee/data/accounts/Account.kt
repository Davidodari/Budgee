package com.odaroid.budgee.data.accounts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    val name: String,
    val target: Long = 0L,
    @PrimaryKey(autoGenerate = true)
    val _id: Long = 0L
)