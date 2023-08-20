package com.carlos.room_study.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carlos.room_study.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM users_table ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Delete()
    suspend fun deleteUser(user: User)
}