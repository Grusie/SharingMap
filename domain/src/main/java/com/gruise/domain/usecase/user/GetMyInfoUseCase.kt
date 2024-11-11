package com.gruise.domain.usecase.user

import com.gruise.domain.repository.UserRepository
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke() = userRepository.getMyInfo()
}