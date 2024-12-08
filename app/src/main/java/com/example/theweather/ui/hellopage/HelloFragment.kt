package com.example.theweather.ui.hellopage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.theweather.R

class HelloFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hello, container, false)

        val buttonContinue: Button = view.findViewById(R.id.buttonContinue)
        buttonContinue.setOnClickListener {
            findNavController().navigate(R.id.action_helloFragment_to_navigation_home)
        }

        return view
    }
}