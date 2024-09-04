package com.grusie.sharingmap.data

import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel

val fakeUserSearch = listOf(
    UserUiModel(
        id = 0L,
        profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
        name = "김민수"
    ),
    UserUiModel(
        id = 1L,
        profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
        name = "신나라"
    ),
    UserUiModel(
        id = 2L,
        profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
        name = "고봉민"
    ),
    UserUiModel(
        id = 3L,
        profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
        name = "이대호"
    ),
    UserUiModel(
        id = 4L,
        profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
        name = "김유정"
    ),

    )

val fakeTagSearch = listOf(
    TagUiModel(
        id = 0L,
        name = "카페",
        count = 10
    ), TagUiModel(
        id = 1L,
        name = "술집",
        count = 10
    ), TagUiModel(
        id = 2L,
        name = "음식점",
        count = 100
    ), TagUiModel(
        id = 3L,
        name = "카페2",
        count = 5
    )
)