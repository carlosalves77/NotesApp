package com.carlos.room_study.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")val id: Int,

    @ColumnInfo(name = "noteTitle")val noteTitle: String,
    @ColumnInfo(name = "note")val note: String,
)
