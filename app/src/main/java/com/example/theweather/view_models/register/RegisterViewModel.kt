package com.example.theweather.view_models.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel(private val dbHelper: DbHelper) : ViewModel() {

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(email: String, login: String, pass: String) {
        viewModelScope.launch {
            // Проверка валидности данных (необязательно)
            //.........

            val user = User(email, login, pass)
            dbHelper.addUser(user)
            _registerResult.value = RegisterResult.Success("Пользователь $login добавлен")
        }
    }

    sealed class RegisterResult {
        data class Success(val message: String) : RegisterResult()
        data class Error(val message: String) : RegisterResult()
    }
}
