package com.carlos.room_study.repositories

import androidx.lifecycle.LiveData
import com.carlos.room_study.db.UserDao
import com.carlos.room_study.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    fun getAllUsers(): LiveData<List<User>> = userDao.getAllUsers()

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

}