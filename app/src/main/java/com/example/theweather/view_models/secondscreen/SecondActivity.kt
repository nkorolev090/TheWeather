package com.example.theweather

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

@Suppress("NAME_SHADOWING")
class SecondActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        val button = findViewById<Button>(R.id.button_to_register_activity)
        button.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

            val button = findViewById<Button>(R.id.button_to_login_activity)
            button.setOnClickListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }
        }
    }
}