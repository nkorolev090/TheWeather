package com.example.theweather.view_models.register

import LoginActivity
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.theweather.R

class RegisterActivity:AppCompatActivity() {
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val userEmail:EditText = findViewById(R.id.email_et)
        val userLogin:EditText = findViewById(R.id.username_et)
        val userPass:EditText = findViewById(R.id.password_et)
        val button: Button = findViewById(R.id.sign_up_btn)
        val linkToLog: TextView = findViewById(R.id.link_to_log)

        linkToLog.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)

            startActivity(intent)
        }

        button.setOnClickListener {
            val email=userEmail.text.toString().trim()
            val login=userLogin.text.toString().trim()
            val pass=userPass.text.toString().trim()

            if(email == "" || login== "" || pass== "" )
                Toast.makeText(this," Не все поля заполнены",Toast.LENGTH_LONG).show()
            else{
                val user= User(email,login,pass)

                val db= DbHelper(this,null)
                db.addUser(user)
                Toast.makeText(this,"Пользователь $login добавлен",Toast.LENGTH_LONG).show()

                userEmail.text.clear()
                userLogin.text.clear()
                userPass.text.clear()
            }

        }

    }
}
//
//class RegisterViewModel(private val dbHelper: DbHelper) : ViewModel() { // Класс ViewModel для регистрации
//
//    private val _registerResult = MutableLiveData<RegisterResult>() // LiveData для хранения результата регистрации
//    val registerResult: LiveData<RegisterResult> = _registerResult // LiveData для наблюдения за результатом
//
//    fun register(email: String, login: String, pass: String) { // Функция для регистрации пользователя
//        if (email.isEmpty() || login.isEmpty() || pass.isEmpty()) { // Проверка, заполнены ли все поля
//            _registerResult.value = RegisterResult.Error("Не все поля заполнены") // Установка ошибки, если поля не заполнены
//            return // Выход из функции
//        }
//
//        val user = User(email, login, pass) // Создание объекта User
//        val success = dbHelper.addUser(user) // Попытка добавить пользователя в базу данных
//
//        _registerResult.value = if (success) { // Проверка результата добавления
//            RegisterResult.Success("Пользователь $login успешно добавлен") // Установка результата Success, если добавление успешно
//        } else {
//            RegisterResult.Error("Ошибка при добавлении пользователя") // Установка результата Error, если добавление не удалось
//        }
//    }
//
//    sealed class RegisterResult { // Класс для хранения результата регистрации
//        data class Success(val message: String) : RegisterResult() // Класс Success с сообщением
//        data class Error(val message: String) : RegisterResult() // Класс Error с сообщением
//    }
//
//}

