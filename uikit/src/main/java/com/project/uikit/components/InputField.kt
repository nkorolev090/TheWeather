package com.project.uikit.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.uikit.R
import com.project.uikit.TheWeatherTheme
import kotlinx.coroutines.delay

@Composable
fun InputField(value: String,
               onValueChange: (String) -> Unit,
               inputType: InputTypes = InputTypes.TEXT,
               modifier: Modifier = Modifier
) {
    var showPassword by remember { mutableStateOf(false) }

    LaunchedEffect(showPassword) {
        if (showPassword) {
            delay(2000)
            showPassword = false
        }
    }

    OutlinedTextField(value = value,
        onValueChange,
        singleLine = true,
        textStyle = MaterialTheme.typography.headlineSmall,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            errorBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledBorderColor = MaterialTheme.colorScheme.onSurfaceVariant,

            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            errorContainerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background,

            focusedTextColor = MaterialTheme.colorScheme.onTertiary,
            disabledTextColor = MaterialTheme.colorScheme.onTertiary,
            unfocusedTextColor = MaterialTheme.colorScheme.onTertiary,
            errorTextColor = MaterialTheme.colorScheme.onTertiary,

            errorTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
            disabledTrailingIconColor = MaterialTheme.colorScheme.onTertiary,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onTertiary
        ),
        shape = RoundedCornerShape(20.dp),
        visualTransformation =
        if (inputType == InputTypes.PASSWORD && !showPassword){
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            when(inputType){
                InputTypes.TEXT -> Unit
                InputTypes.EMAIL -> Unit
                InputTypes.PASSWORD -> {
                    IconButton(onClick = { showPassword = !showPassword }) {
                        Icon(painter = if(showPassword) painterResource(R.drawable.ic_visibility_off)  else painterResource(R.drawable.ic_visibility), contentDescription = "Show Password")
                    }
                }
                InputTypes.DATE -> {
                    Icon(painter = painterResource(R.drawable.ic_calendar), contentDescription = null)
                }
            }
        }
    )
}

@Preview
@Composable
private fun InputFieldPreview(){
    TheWeatherTheme {
        InputField("nikitaKorolev@mail.ru", {}, inputType = InputTypes.PASSWORD)
    }
}
