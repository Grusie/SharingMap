package com.gruise.data.service

import com.gruise.data.model.UserDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("api/users/@me")
    suspend fun getMyInfo(): Result<UserDto>

    @GET("api/users/{userId}")
    suspend fun getUserByUserId(
        @Path("userId") userId: Long
    ): Result<UserDto>
}