package com.gruise.domain.repository

interface MapRepository {
    suspend fun getAddress(latitude: Double, longitude: Double): Result<String>
}