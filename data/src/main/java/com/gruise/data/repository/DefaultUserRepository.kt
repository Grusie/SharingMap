package com.gruise.data.repository

import com.gruise.data.datasource.user.UserRemoteDataSource
import com.gruise.data.mapper.toDomain
import com.gruise.domain.model.User
import com.gruise.domain.repository.UserRepository
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
): UserRepository {
    override suspend fun getMyInfo(): Result<User> {
        return userRemoteDataSource.getMyInfo().map { it.toDomain() }
    }

    override suspend fun getUserByUserId(userId: Long): Result<User> {
        return userRemoteDataSource.getUserByUserId(userId).map { it.toDomain() }
    }
}