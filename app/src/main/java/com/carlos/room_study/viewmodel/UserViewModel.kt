package com.carlos.room_study.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.carlos.room_study.db.AppDatabase
import com.carlos.room_study.model.User
import com.carlos.room_study.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    var getAllUser: LiveData<List<User>>

    init {
        val userDB = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDB)
        getAllUser = repository.getAllUsers()
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }

    fun deleteUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUserById(userId)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<User>> = repository.searchDatabase(searchQuery)

}