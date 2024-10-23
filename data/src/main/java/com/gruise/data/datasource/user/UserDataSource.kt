package com.gruise.data.datasource.user

import com.gruise.data.model.UserDto

interface UserDataSource {
    suspend fun getMyInfo(): Result<UserDto>

}