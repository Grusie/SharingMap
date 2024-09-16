package com.gruise.domain.model

data class User(
    val userId: Long,
    val profileImage: String,
    val name: String,
    val description: String,
    val email: String,
)
