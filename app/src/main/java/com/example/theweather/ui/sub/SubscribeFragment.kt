package com.example.theweather.ui.sub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.theweather.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubscribeFragment : Fragment() {

    private lateinit var backButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_subscribe, container, false)

        backButton = view.findViewById(R.id.backButton1)

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return view
    }
}