package com.grusie.sharingmap.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.grusie.sharingmap.R

val pretendard =
    FontFamily(
        Font(R.font.pretendard_medium, FontWeight.Medium, FontStyle.Normal),
        Font(R.font.pretendard_regular, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.pretendard_bold, FontWeight.Bold, FontStyle.Normal),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold, FontStyle.Normal),
    )

/**
 *  400 - Normal
 *  500 - Medium
 *  600 - SemiBold
 *  700 - Bold
 * **/
// Set of Material typography styles to start with
val Typography =
    Typography(
        displayLarge =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        displayMedium =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.1).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        headlineLarge =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.2).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        headlineMedium =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.3).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        headlineSmall =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        titleLarge =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-0.3).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        titleMedium =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                letterSpacing = (-0.2).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        titleSmall =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        bodyLarge =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal, // Regular
                letterSpacing = (-0.1).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        bodyMedium =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        bodySmall =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-0.1).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        labelLarge =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = (-0.2).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        labelMedium =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal, // Regular
                letterSpacing = (-0.2).sp,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
        labelSmall =
            TextStyle(
                fontFamily = pretendard,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                platformStyle =
                    PlatformTextStyle(
                        includeFontPadding = false,
                    ),
            ),
    )
