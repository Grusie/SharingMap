package com.grusie.sharingmap.ui.screen.mypage

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.grusie.sharingmap.BaseTest
import com.grusie.sharingmap.R
import com.grusie.sharingmap.ui.main.mypage.MyPageScreen
import com.grusie.sharingmap.ui.main.mypage.MyPageUiState
import com.grusie.sharingmap.ui.model.UserUiModel
import org.junit.Before
import org.junit.Test

class MyPageScreenTest : BaseTest() {

    private var state = mutableStateOf(MyPageUiState())
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyPageScreen(
                uiState = state.value,
                snackbarHostState = SnackbarHostState(),
                updateSelectedTabIndex = {},
                updateIsStorageBottomSheetOpen = {},
                onUserClick = {},
                onStorageClick = {})
        }
    }

    @Test
    fun 화면에_유저_이름이_두번_보여진다() {
        state.value = MyPageUiState(user = UserUiModel(name = "김민수"))

        val nodes = composeTestRule.onAllNodesWithText("김민수").fetchSemanticsNodes()
        assert(nodes.size == 2)

        composeTestRule.onAllNodesWithText("김민수")[0].assertIsDisplayed()
        composeTestRule.onAllNodesWithText("김민수")[1].assertIsDisplayed()
    }

    @Test
    fun 유저_설명이_50글자를_초과하면_유저_설명이_50글자로_제한되고_말줄임표시와_함께_더보기_글자가_활성화된다() {
        state.value = MyPageUiState(user = UserUiModel(description = "안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다."))

        composeTestRule.onNodeWithText("안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드...").isDisplayed()
        composeTestRule.onNodeWithText("더보기").isDisplayed()
    }

    @Test
    fun 유저_설명이_50글자를_초과하고_더보기_글자를_클릭하면_유저_설명이_전체가_보이고_접기_글자가_활성화된다() {
        state.value = MyPageUiState(user = UserUiModel(description = "안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다."))

        // 더보기 글자가 보이고 클릭
        composeTestRule.onNodeWithText("더보기").isDisplayed()
        composeTestRule.onNodeWithContentDescription(context.getString(R.string.mypage_limited_text_button)).performClick()

        composeTestRule.onNodeWithText("안녕하세요. 김민수입니다. 잘 부탁드립니다. 안녕하세요. 김민수입니다. 잘 부탁드립니다.").isDisplayed()
        composeTestRule.onNodeWithText("접기").isDisplayed()
        composeTestRule.onNodeWithText("더보기").isNotDisplayed()
    }

    @Test
    fun 로딩_상태에서_로딩_프로그레스가_보여진다() {
        state.value = MyPageUiState(isLoading = true)

        composeTestRule.onNodeWithContentDescription(context.getString(R.string.loading_prgress)).isDisplayed()
    }

    @Test
    fun 로딩_상태가_아니라면_로딩_프로그레스가_보이지않아야_한다() {
        state.value = MyPageUiState(isLoading = false)

        composeTestRule.onNodeWithContentDescription(context.getString(R.string.loading_prgress)).isNotDisplayed()
    }

    @Test
    fun 에러가_발생하면_스낵바에_에러문구가_보여진다() {
        state.value = MyPageUiState(errorMessage = "에러가 발생했습니다.")
        composeTestRule.onNodeWithText("에러가 발생했습니다.").isDisplayed()
    }

}