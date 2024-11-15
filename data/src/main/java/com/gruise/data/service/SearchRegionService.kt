package com.gruise.data.service

import com.gruise.data.BuildConfig
import com.gruise.data.model.SearchRegionsDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchRegionService {
    @GET("v2/local/search/keyword")
    suspend fun getSearchRegionList(
        @Query("query") query: String,
        @Header("Authorization") authorization: String = BuildConfig.KAKAO_REST_API_KEY,
    ): SearchRegionsDto
}