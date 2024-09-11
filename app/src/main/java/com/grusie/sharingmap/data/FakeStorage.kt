package com.grusie.sharingmap.data

import com.grusie.sharingmap.ui.model.StorageUiModel

val fakeStorage = listOf(
    StorageUiModel(
        id = 1,
        title = "보관함1",
        count = 10,
        feeds = fakeFeeds
    ),
    StorageUiModel(
        id = 2,
        title = "보관함2",
        count = 20
    ),
    StorageUiModel(
        id = 3,
        title = "보관함3",
        count = 30
    )
)
