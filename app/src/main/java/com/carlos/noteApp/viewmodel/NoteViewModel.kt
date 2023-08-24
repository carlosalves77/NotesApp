package com.carlos.noteApp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.carlos.noteApp.db.AppDatabase
import com.carlos.noteApp.model.Note
import com.carlos.noteApp.repositories.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    var getAllNote: LiveData<List<Note>>

    init {
        val userDB = AppDatabase.getDatabase(application).userDao()
        repository = NoteRepository(userDB)
        getAllNote = repository.getAllUsers()
    }

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(note)
        }
    }

    fun deleteNote(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserById(userId)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(note)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Note>> = repository.searchDatabase(searchQuery)

}