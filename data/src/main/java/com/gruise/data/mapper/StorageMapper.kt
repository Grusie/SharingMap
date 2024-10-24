package com.gruise.data.mapper

import com.gruise.data.model.StorageDto
import com.gruise.domain.model.Storage

fun StorageDto.toDomain(): Storage {
    return Storage(id = id, name = name, count = count)
}