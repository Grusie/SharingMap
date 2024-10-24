package com.grusie.sharingmap.ui.mapper

import com.gruise.domain.model.Storage
import com.grusie.sharingmap.ui.model.StorageUiModel

fun Storage.toUiModel(): StorageUiModel {
    return StorageUiModel(id = id, title = name, count = count)
}