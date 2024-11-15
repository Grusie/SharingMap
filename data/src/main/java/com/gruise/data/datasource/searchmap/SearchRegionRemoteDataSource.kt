package com.gruise.data.datasource.searchmap

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.gson.Gson
import com.gruise.data.model.DataStoreKeys
import com.gruise.data.model.SearchRegionsDto
import com.gruise.data.service.SearchRegionService
import com.gruise.domain.model.SearchRegion
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRegionRemoteDataSource @Inject constructor(
    private val searchRegionService: SearchRegionService,
    private val dataStore: DataStore<Preferences>,
) : SearchRegionDataSource {
    private val gson = Gson()

    override suspend fun getSearchRegionList(keyword: String): Result<SearchRegionsDto> {
        return try {
            val response = searchRegionService.getSearchRegionList(
                keyword
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveSearchRegionHistory(searchRegion: SearchRegion): Result<Nothing?> {
        val currentHistoryJson: Set<String> = dataStore.data.map { preferences ->
            preferences[DataStoreKeys.SearchRegionHistory.SEARCH_REGION_LIST] ?: emptySet<String>()
        }
            .first()

        val currentHistory = currentHistoryJson.mapNotNull { json ->
            gson.fromJson(json, SearchRegion::class.java)
        }.toMutableList()

        // 새로운 SearchRegion을 리스트에 추가
        currentHistory.add(searchRegion)

        // 리스트를 다시 JSON으로 변환하여 저장
        val updatedHistoryJson = currentHistory.map { gson.toJson(it) }

        // DataStore에 업데이트된 리스트 저장
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.SearchRegionHistory.SEARCH_REGION_LIST] =
                updatedHistoryJson.toSet()
        }

        return Result.success(null)
    }

    override suspend fun getSearchRegionHistory(): Result<List<SearchRegion>> {
        // 현재 저장된 JSON 데이터 불러오기
        val historyJson: Set<String> = dataStore.data
            .map { preferences ->
                preferences[DataStoreKeys.SearchRegionHistory.SEARCH_REGION_LIST]
                    ?: emptySet<String>()
            }
            .first()  // 첫 번째 값만 받기

        // JSON 문자열을 SearchRegion 객체로 변환하여 리스트로 반환
        val historyList = historyJson.mapNotNull { json ->
            gson.fromJson(json, SearchRegion::class.java)
        }

        return Result.success(historyList)
    }

    override suspend fun clearSearchRegionHistory(): Result<Nothing?> {
        // DataStore에서 검색 기록을 삭제
        dataStore.edit { preferences ->
            preferences[DataStoreKeys.SearchRegionHistory.SEARCH_REGION_LIST] = emptySet()
        }

        return Result.success(null)
    }
}
