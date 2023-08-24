package com.carlos.noteApp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.carlos.noteApp.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM note_table WHERE id = :id")
    suspend fun deleteNoteByID(id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note_table WHERE noteTitle LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<Note>>
}