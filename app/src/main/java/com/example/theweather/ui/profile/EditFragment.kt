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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        saveButton.setOnClickListener {

            findNavController().popBackStack()
        }
        return view
    }

}