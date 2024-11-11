package com.gruise.data.datasource.user

import com.gruise.data.model.UserDto
import com.gruise.data.service.UserService
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val userService: UserService
): UserDataSource {
    override suspend fun getMyInfo(): Result<UserDto> {
        return userService.getMyInfo()
    }
}