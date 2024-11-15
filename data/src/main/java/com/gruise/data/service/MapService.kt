package com.gruise.data.service

import com.gruise.data.model.ReverseGeoCodingDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MapService {
    @GET("/map-reversegeocode/v2/gc")
    suspend fun getAddress(
        @Header("x-ncp-apigw-api-key-id") accessKey: String,
        @Header("x-ncp-apigw-api-key") secretKey: String,
        @Query("coords") coords: String?,
        @Query("output") output: String?
    ): ReverseGeoCodingDto
}