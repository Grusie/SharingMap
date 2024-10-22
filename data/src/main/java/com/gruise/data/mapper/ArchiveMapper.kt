package com.gruise.data.mapper

import com.gruise.data.model.ArchiveAttachDto
import com.gruise.data.model.ArchiveDto
import com.gruise.domain.model.Archive
import com.gruise.domain.model.ArchiveAttach

fun Archive.toDto(): ArchiveDto {
    return ArchiveDto(
        id = id,
        author = author.toDto(),
        positionX = positionX,
        positionY = positionY,
        address = address,
        name = name,
        content = content,
        isPublic = isPublic,
        archiveAttaches = archiveAttaches.map { it.toDto() },
        tags = tags.map { it.toDto() }
    )
}

fun ArchiveDto.toDomain(): Archive {
    return Archive(
        id = id,
        author = author.toDomain(),
        positionX = positionX,
        positionY = positionY,
        address = address,
        name = name,
        content = content,
        isPublic = isPublic,
        archiveAttaches = archiveAttaches.map { it.toDomain() },
        tags = tags.map { it.toDomain() }
    )
}

fun ArchiveAttach.toDto(): ArchiveAttachDto {
    return ArchiveAttachDto(
        name = name,
        path = path,
        width = width,
        height = height
    )
}

fun ArchiveAttachDto.toDomain(): ArchiveAttach {
    return ArchiveAttach(
        name = name,
        path = path,
        width = width,
        height = height
    )
}