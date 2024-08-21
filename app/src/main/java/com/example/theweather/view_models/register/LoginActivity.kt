import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.theweather.MainActivity
import com.example.theweather.R
import com.example.theweather.view_models.register.DbHelper
import com.example.theweather.view_models.register.RegisterActivity
import com.example.theweather.view_models.register.User
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userEmail: EditText = findViewById(R.id.email_lg)
        val userPass: EditText = findViewById(R.id.password_lg)
        val button: Button = findViewById(R.id.login_btn)
        val linkToReg: TextView = findViewById(R.id.link_to_reg)

        linkToReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        button.setOnClickListener {
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()

            viewModel.login(email, pass)

            viewModel.loginResult.observe(this) { result ->
                when (result) {
                    is LoginViewModel.LoginResult.Success -> {
                        Toast.makeText(this, "Пользователь ${result.user.login} авторизован", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    is LoginViewModel.LoginResult.Error -> {
                        Toast.makeText(this, result.message, Toast.LENGTH_LONG).show()
                    }

                    else -> {}
                }
            }
        }
    }
}

class LoginViewModel(private val dbHelper: DbHelper) : ViewModel() {
    // класс ViewModel для логики входа

    private val _loginResult = MutableLiveData<LoginResult>()
    // объявляем LiveData для хранения результата входа
    val loginResult: LiveData<LoginResult> = _loginResult
    // предоставляем LiveData для наблюдения


    fun login(email: String, pass: String) {
        viewModelScope.launch {
            // функция для проверки учетных данных
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
