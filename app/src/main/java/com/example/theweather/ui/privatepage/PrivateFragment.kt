package com.example.theweather.ui.privatepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.theweather.R

class PrivateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_private, container, false)
        val backButton1: Button = view.findViewById(R.id.back_button1)

        backButton1.setOnClickListener {
            findNavController().popBackStack()
        }
        return view
    }
}