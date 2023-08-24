package com.carlos.noteApp.repositories

import androidx.lifecycle.LiveData
import com.carlos.noteApp.db.NoteDao
import com.carlos.noteApp.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    suspend fun insertUser(note: Note) {
        noteDao.insertNote(note)
    }

    fun getAllUsers(): LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun deleteUserById(user: Int) {
        noteDao.deleteNoteByID(user)
    }

    suspend fun updateUser(note: Note) {
        noteDao.updateNote(note)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Note>> = noteDao.searchDatabase(searchQuery)

}