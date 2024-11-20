package com.gruise.domain.usecase.user

import com.gruise.domain.model.User
import com.gruise.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByUserIdUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(userId: Long): Result<User> {
        return userRepository.getUserByUserId(userId)
    }
}