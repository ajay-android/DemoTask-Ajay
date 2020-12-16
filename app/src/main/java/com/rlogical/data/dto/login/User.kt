package com.rlogical.data.dto.login


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class User {

    @ColumnInfo()
    var userId: String = ""

    @ColumnInfo()
    var userName: String = ""

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}