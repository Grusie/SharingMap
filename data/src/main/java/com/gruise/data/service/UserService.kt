package com.gruise.data.service

import com.gruise.data.model.UserDto
import retrofit2.http.GET

interface UserService {
    @GET("api/users/@me")
    suspend fun getMyInfo(): Result<UserDto>
}