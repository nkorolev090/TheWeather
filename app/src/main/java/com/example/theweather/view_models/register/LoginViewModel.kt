package com.example.theweather.view_models.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel(private val dbHelper: DbHelper) : ViewModel() {

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            val user = dbHelper.getUser(email, pass)
            _loginResult.value = if (user != null) {
                LoginResult.Success(user)
            } else {
                LoginResult.Error("Неверный email или пароль")
            }
        }
    }

    sealed class LoginResult {
        data class Success(val user: User) : LoginResult()
        data class Error(val message: String) : LoginResult()
    }
}
