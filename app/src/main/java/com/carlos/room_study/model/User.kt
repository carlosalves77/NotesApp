package com.carlos.room_study.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")val id: Int,

    @ColumnInfo(name = "First name")val firstName: String,
    @ColumnInfo(name = "Last name")val lastName: String,
    @ColumnInfo(name = "Age")val age: Int
)
