package com.grusie.sharingmap.data

import com.grusie.sharingmap.ui.model.CommentUiModel
import com.grusie.sharingmap.ui.model.FeedInfoUiModel
import com.grusie.sharingmap.ui.model.FeedUiModel
import com.grusie.sharingmap.ui.model.LocationUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import java.time.LocalDate

val fakeFeeds =
    listOf(
        FeedUiModel(
            id = 1,
            user =
                UserUiModel(
                    id = 1,
                    profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    name = "김민수",
                ),
            date = "2024.04.17",
            content = "honestatis",
            archivings =
                listOf(
                    UserUiModel(
                        id = 2,
                        profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                        name = "장원철",
                    ),
                    UserUiModel(
                        id = 3,
                        profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                        name = "장원철",
                    ),
                ),
            contentImages =
                listOf(
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                ),
            location =
                LocationUiModel(
                    name = "Lilian Douglas",
                    address = "taciti",
                ),
            feedInfo =
                FeedInfoUiModel(
                    likeCount = 2617,
                    chatCount = 1606,
                    shareCount = 8829,
                ),
            comments = listOf(
                CommentUiModel(
                    id = 1,
                    user =
                    UserUiModel(
                        id = 1,
                        profileImage = "",
                        name = "김민수",
                    ),
                    content = "안녕하세요",
                    date = LocalDate.now(),
                ),
                CommentUiModel(
                    id = 2,
                    user =
                    UserUiModel(
                        id = 1,
                        profileImage = "",
                        name = "김민수",
                    ),
                    content = "안녕하세요",
                    date = LocalDate.now(),
                ),
            ),
        ),
        FeedUiModel(
            id = 2,
            user =
                UserUiModel(
                    id = 2,
                    profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    name = "장원철",
                ),
            date = "2024.04.17",
            content = "honestatis",
            contentImages =
                listOf(
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                ),
            location =
                LocationUiModel(
                    name = "Lilian Douglas",
                    address = "taciti",
                ),
            feedInfo =
                FeedInfoUiModel(
                    likeCount = 2617,
                    chatCount = 1606,
                    shareCount = 8829,
                ),
        ),
        FeedUiModel(
            id = 3,
            user =
                UserUiModel(
                    id = 3,
                    profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    name = "신나라",
                ),
            date = "2024.04.17",
            content = "honestatis",
            contentImages =
                listOf(
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                ),
            location =
                LocationUiModel(
                    name = "Lilian Douglas",
                    address = "taciti",
                ),
            feedInfo =
                FeedInfoUiModel(
                    likeCount = 2617,
                    chatCount = 1606,
                    shareCount = 8829,
                ),
        ),
    )
