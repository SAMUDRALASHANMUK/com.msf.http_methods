package com.msf.domain.interfaces

import com.msf.data.model.UserLogin

interface UserLoginRepository {
    suspend fun createUser(userName:String,password:String): UserLogin?
    suspend fun getUser(userName:String,password:String): UserLogin?
}
