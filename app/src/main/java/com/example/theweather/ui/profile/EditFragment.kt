package com.example.theweather.ui.profile

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.theweather.R

class EditFragment: Fragment() {
    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var backButton: Button
    private lateinit var saveButton: Button
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit,container,false)
        editName = view.findViewById(R.id.edit_name)
        editEmail = view.findViewById(R.id.edit_EmailAddress)
        editPassword = view.findViewById(R.id.edit_Password)
        backButton = view.findViewById(R.id.back_btn)
        saveButton = view.findViewById(R.id.save_changes_btn)
        sharedPreferences = requireActivity().getSharedPreferences("UserPrefs",Context.MODE_PRIVATE)

        loadUserData()
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        saveButton.setOnClickListener {
            saveUserData()
            findNavController().popBackStack()
        }
        return view
    }
    private fun loadUserData(){
        val name = sharedPreferences.getString("name", "Melissa Peters")
        val email = sharedPreferences.getString("email", "email@gmail.com")
        val password = sharedPreferences.getString("password","password")
        editName.setText(name)
        editEmail.setText(email)
        editPassword.setText(password)

    }
    private fun saveUserData() {
        val editor = sharedPreferences.edit()
        editor.putString("name", editName.text.toString())
        editor.putString("email", editEmail.text.toString())
        editor.putString("password", editPassword.text.toString())
        editor.apply()
    }
}