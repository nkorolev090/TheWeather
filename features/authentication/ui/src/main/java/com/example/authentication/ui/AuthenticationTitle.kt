package com.example.authentication.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AuthenticationTitle(text: String, modifier: Modifier = Modifier) {
    Text(text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onPrimary,
        textAlign = TextAlign.Center)
}