package com.project.uikit

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

private val anonymousFontFamily = FontFamily(listOf(
    Font(
        resId = R.font.anonymous_pro_bold,
        weight = FontWeight.Bold,
        style = FontStyle.Normal,
    ),
    Font(
        resId = R.font.anonymous_pro_bold_italic,
        weight = FontWeight.Bold,
        style = FontStyle.Italic,
    ),
    Font(
        resId = R.font.anonymous_pro_regular,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    ),
    Font(
        resId = R.font.anonymous_pro_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic,
    )
))

private val interFontFamily = FontFamily(listOf(
    Font(
        resId = R.font.inter,
        weight = FontWeight.Normal,
        style = FontStyle.Normal,
    ),
    Font(
        resId = R.font.inter_italic,
        weight = FontWeight.Normal,
        style = FontStyle.Italic,
    ),
))

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = anonymousFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 64.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 36.sp,
        letterSpacing = 0.4.sp
    ),
    titleSmall = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 24.sp,
        letterSpacing = 0.3.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.2.sp
    ),
    labelMedium = TextStyle(
        fontFamily = anonymousFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        letterSpacing = 1.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = interFontFamily,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Normal,
        fontSize = 20.sp,
        letterSpacing = 0.3.sp
    ),
    labelLarge = TextStyle(
        fontFamily = anonymousFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        fontSize = 40.sp,
        letterSpacing = 0.3.sp
    ),
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)