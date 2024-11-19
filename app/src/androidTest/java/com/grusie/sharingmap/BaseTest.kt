package com.grusie.sharingmap

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

open class BaseTest {
    @get:Rule
    val composeTestRule = createComposeRule()
}