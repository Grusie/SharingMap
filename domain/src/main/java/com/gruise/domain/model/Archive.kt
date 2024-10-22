package com.gruise.domain.model

data class Archive(
    val id: Long,
    val author: User,
    val positionX: Double,
    val positionY: Double,
    val address: String,
    val name: String,
    val content: String,
    val isPublic: Boolean,
    val archiveAttaches: List<ArchiveAttach>,
    val tags: List<Tag>,
)
