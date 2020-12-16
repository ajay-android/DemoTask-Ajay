package com.rlogical.data.dto.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    fun insertUser(vararg users: User?)

    @Query(value = "Select * from user_table WHERE userId = :userId")
    fun isExitsUser(userId: String) : Boolean

    @Query(value = "Select * from user_table")
    fun getAllUser() : List<User>

}

