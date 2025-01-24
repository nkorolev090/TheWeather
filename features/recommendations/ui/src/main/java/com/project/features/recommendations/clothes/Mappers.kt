package com.project.features.recommendations.clothes

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.firebaseapi.clothes.models.enums.StyleEnum
import com.project.features.recommendations.ui.R

@Composable
fun StyleEnum.toStringResource() : String =
    when(this){
        StyleEnum.OFFICIAL -> stringResource(R.string.officialStyle)
        StyleEnum.SPORT -> stringResource(R.string.sportStyle)
    }
