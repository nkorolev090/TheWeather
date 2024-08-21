package com.example.theweather.view_models.register

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory? = null) :
    SQLiteOpenHelper(context, "app", factory, 1) {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, login TEXT, pass TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("email", user.email)
        values.put("login", user.login)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    @SuppressLint("Recycle")
    fun getUser(email: String, pass: String): User? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM users WHERE email = '$email' AND pass = '$pass'", null)
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(0)
            val email = cursor.getString(1)
            val login = cursor.getString(2)
            val pass = cursor.getString(3)
            return User(email, login, pass)
        }
        return null // Возвращаем null, если пользователь не найден
    }

    fun updateUser(user: User) {
        val values = ContentValues()
        values.put("email", user.email)
        values.put("login", user.login)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.update("users", values, "id = ${user.id}", null)
        db.close()
    }

    fun deleteUser(id: Int) {
        val db = this.writableDatabase
        db.delete("users", "id = $id", null)
        db.close()
    }
}