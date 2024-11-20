package com.gruise.domain.usecase.user

data class UserUseCase(
    val getMyInfoUseCase: GetMyInfoUseCase,
    val getUserByUserIdUseCase: GetUserByUserIdUseCase
)