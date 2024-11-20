package com.gruise.domain.repository

import com.gruise.domain.model.User

interface UserRepository {
    suspend fun getMyInfo(): Result<User>
    suspend fun getUserByUserId(userId: Long): Result<User>
}