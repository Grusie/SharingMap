package com.gruise.domain.model

data class User(
    val userId: Long,
    val profileImage: String,
    val name: String,
    val description: String? = null,
    val email: String?,
    val follow: Boolean? = null,
)
