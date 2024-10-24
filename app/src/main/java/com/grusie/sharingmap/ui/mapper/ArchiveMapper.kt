package com.grusie.sharingmap.ui.mapper

import com.gruise.domain.model.Archive
import com.gruise.domain.model.ArchiveAttach
import com.grusie.sharingmap.ui.model.ArchiveAttachUiModel
import com.grusie.sharingmap.ui.model.ArchiveInfoUiModel
import com.grusie.sharingmap.ui.model.ArchiveUiModel
import com.grusie.sharingmap.ui.model.LocationUiModel

fun Archive.toUiModel(): ArchiveUiModel {
    return ArchiveUiModel(
        id = id,
        user = author.toUiModel(),
        date = "2024.10.24",
        content = content,
        archivings = emptyList(),
        archiveAttaches = archiveAttaches.map { it.toUiModel() },
        location = LocationUiModel(name = name, address = address, positionX = positionX, positionY = positionY),
        feedInfo = ArchiveInfoUiModel(likeCount = 0, chatCount = 0, shareCount = 0)
    )
}

fun ArchiveAttach.toUiModel(): ArchiveAttachUiModel {
    return ArchiveAttachUiModel(name = name, path = path, width = width, height = height)
}