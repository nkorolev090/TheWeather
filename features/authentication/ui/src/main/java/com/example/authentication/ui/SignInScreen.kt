package com.example.authentication.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authentication.ui_logic.SignInViewModel
import com.project.uikit.components.InputField
import com.project.uikit.components.InputTypes

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    SignInScreen(modifier = modifier, viewModel = viewModel())
}
@Composable
internal fun SignInScreen(modifier: Modifier = Modifier, viewModel: SignInViewModel){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.gradient2_bg),
                contentScale = ContentScale.Fit
            )
            .padding(horizontal = 35.dp),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AuthenticationTitle(text = stringResource(R.string.sign_in_header))
        InputField(value = email, onValueChange = { value -> email = value}, inputType = InputTypes.EMAIL, modifier = Modifier.padding(top = 32.dp))
        InputField(value = password, onValueChange = { value -> password = value}, inputType = InputTypes.PASSWORD,  modifier = Modifier.padding(top = 12.dp))
    }
}