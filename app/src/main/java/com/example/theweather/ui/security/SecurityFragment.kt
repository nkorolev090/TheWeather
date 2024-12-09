package com.example.theweather.ui.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.theweather.R

class SecurityFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_security, container, false)
        val backButton: Button = view.findViewById(R.id.back_button)

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return view
    }
}