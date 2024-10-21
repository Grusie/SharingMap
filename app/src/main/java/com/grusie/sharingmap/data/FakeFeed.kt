package com.grusie.sharingmap.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.grusie.sharingmap.ui.model.CommentUiModel
import com.grusie.sharingmap.ui.model.ArchiveInfoUiModel
import com.grusie.sharingmap.ui.model.ArchiveUiModel
import com.grusie.sharingmap.ui.model.LocationUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
val fakeFeeds =
    listOf(
        ArchiveUiModel(
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
            archiveAttaches =
                listOf(
                ),
            location =
                LocationUiModel(
                    name = "Lilian Douglas",
                    address = "taciti",
                    positionX = 0.0,
                    positionY = 0.0
                ),
            feedInfo =
                ArchiveInfoUiModel(
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
        ArchiveUiModel(
            id = 2,
            user =
                UserUiModel(
                    id = 2,
                    profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    name = "장원철",
                ),
            date = "2024.04.17",
            content = "honestatis",
            archiveAttaches =
            listOf(
            ),
            location =
            LocationUiModel(
                name = "Lilian Douglas",
                address = "taciti",
                positionX = 0.0,
                positionY = 0.0
            ),
            feedInfo =
                ArchiveInfoUiModel(
                    likeCount = 2617,
                    chatCount = 1606,
                    shareCount = 8829,
                ),
        ),
        ArchiveUiModel(
            id = 3,
            user =
                UserUiModel(
                    id = 3,
                    profileImage = "https://img.freepik.com/free-photo/adorable-kitty-looking-like-it-want-to-hunt_23-2149167099.jpg?w=2000",
                    name = "신나라",
                ),
            date = "2024.04.17",
            content = "honestatis",
            archiveAttaches =
            listOf(
            ),
            location =
            LocationUiModel(
                name = "Lilian Douglas",
                address = "taciti",
                positionX = 0.0,
                positionY = 0.0
            ),
            feedInfo =
                ArchiveInfoUiModel(
                    likeCount = 2617,
                    chatCount = 1606,
                    shareCount = 8829,
                ),
        ),
    )
