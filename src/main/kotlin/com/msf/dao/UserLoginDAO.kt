package com.msf.dao

import com.msf.model.UserLogin

interface UserLoginDAO {
    suspend fun createUser(userName:String,password:String): UserLogin?
    suspend fun getUser(userName:String,password:String): UserLogin?
}
