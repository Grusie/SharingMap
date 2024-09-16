package com.gruise.data.service

import com.gruise.data.model.TagsDto
import com.gruise.data.model.UsersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("api/users")
    suspend fun getUserSearch(@Query("q") query: String, @Query("limit") limit: Int): Result<UsersDto>

    @GET("api/tags")
    suspend fun getTagSearch(@Query("q") query: String, @Query("limit") limit: Int): Result<TagsDto>
}