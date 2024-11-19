package com.grusie.sharingmap.ui.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithText
import com.grusie.sharingmap.BaseTest
import com.grusie.sharingmap.ui.main.search.SearchScreen
import com.grusie.sharingmap.ui.main.search.SearchUiState
import com.grusie.sharingmap.ui.model.TagUiModel
import com.grusie.sharingmap.ui.model.UserUiModel
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalFoundationApi::class)
class SearchScreenTest : BaseTest() {

    private var state = mutableStateOf(SearchUiState())
    private val fakeUserData = listOf(
        UserUiModel(
            id = 1L,
            profileImage = "",
            name = "이하은",
            description = "안녕하세요, 반갑습니다",
            email = "",
            followerCount = 50,
            postCount = 20,
            follow = false
        ),
        UserUiModel(
            id = 2L,
            profileImage = "",
            name = "이하영",
            description = "안녕하세요, 반갑습니다2",
            email = "",
            followerCount = 50,
            postCount = 20,
            follow = false
        )
    )

    private val fakeTagData = listOf(
        TagUiModel(
            id = 1L,
            name = "식당",
            count = 30,
        ),
        TagUiModel(
            id = 2L,
            name = "식품관",
            count = 30,
        )
    )

    @Before
    fun setUp() {
        state.value = SearchUiState(userSearch = fakeUserData, tagSearch = fakeTagData)
        composeTestRule.setContent {
            SearchScreen(
                uiState = state.value,
                snackbarHostState = SnackbarHostState(),
                searchTextField = TextFieldState(),
                updateSelectedTabIndex = {},
                insertUserSearchHistory = {},
                insertTagSearchHistory = {},
                deleteAllUerSearchHistory = { /*TODO*/ },
                deleteAllTagSearchHistory = { /*TODO*/ },
                onNavigationClick = { /*TODO*/ },
                onUserItemClick = {},
                onTagItemClick = {}
            )
        }

    }

    @Test
    fun 탭이_사람으로_되어있고_입력된_텍스트가_없으면_유저_검색_기록만_노출된다() {
        state.value = state.value.copy(searchTextField = TextFieldState(initialText = ""), selectedTabIndex = 0)
        composeTestRule.onNodeWithText("이하은").assertIsDisplayed()
        composeTestRule.onNodeWithText("이하영").assertIsDisplayed()

        composeTestRule.onNodeWithText("식당 +30").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("식품관 +30").assertIsNotDisplayed()
    }

    @Test
    fun 탭이_사람으로_되어있고_입력된_텍스트가_있으면_유저_검색_결과만_노출된다() {
        state.value = state.value.copy(searchTextField = TextFieldState(initialText = "이"), selectedTabIndex = 0)
        composeTestRule.onNodeWithText("이하은").assertIsDisplayed()
        composeTestRule.onNodeWithText("이하영").assertIsDisplayed()

        composeTestRule.onNodeWithText("식당 +30").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("식품관 +30").assertIsNotDisplayed()
    }

    @Test
    fun 탭이_태그로_되어있고_입력된_텍스트가_없으면_태그_검색_기록만_노출된다() {
        state.value = state.value.copy(searchTextField = TextFieldState(initialText = ""), selectedTabIndex = 1)
        composeTestRule.onNodeWithText("식당 +30").assertIsDisplayed()
        composeTestRule.onNodeWithText("식품관 +30").assertIsDisplayed()

        composeTestRule.onNodeWithText("이하은").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("이하영").assertIsNotDisplayed()
    }

    @Test
    fun 탭이_태그로_되어있고_입력된_텍스트가_없으면_태그_검색_결과만_노출된다() {
        state.value = state.value.copy(searchTextField = TextFieldState(initialText = "식"), selectedTabIndex = 1)
        composeTestRule.onNodeWithText("식당 +30").assertIsDisplayed()
        composeTestRule.onNodeWithText("식품관 +30").assertIsDisplayed()

        composeTestRule.onNodeWithText("이하은").assertIsNotDisplayed()
        composeTestRule.onNodeWithText("이하영").assertIsNotDisplayed()
    }
}