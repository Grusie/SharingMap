package com.gruise.data.mapper

import com.gruise.data.model.TagDto
import com.gruise.domain.model.Tag

fun TagDto.toDomain(): Tag {
    return Tag(
        tagId = tagId,
        tagName = tagName,
        tagCount = tagCount
    )
}

fun Tag.toDto(): TagDto {
    return TagDto(
        tagId = tagId,
        tagName = tagName,
        tagCount = tagCount
    )
}