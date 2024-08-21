package com.example.theweather.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.theweather.R

class ProfileFragment: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val privacyButton = view.findViewById<Button>(R.id.buttonPrivacy)
        val subscribesButton = view.findViewById<Button>(R.id.buttonSubscribes)
        val helpButton = view.findViewById<Button>(R.id.buttonHelp)
        val notificationButton = view.findViewById<Button>(R.id.buttonNotification)
        val userdataButton = view.findViewById<Button>(R.id.buttonUserData)


        privacyButton.setOnClickListener {
            val intent = Intent(requireContext(),SubscribeActivity::class.java)
            startActivity(intent)
        }

        subscribesButton.setOnClickListener {
            val intent = Intent(requireContext(),SubscribeActivity::class.java)
            startActivity(intent)
        }

        helpButton.setOnClickListener {
            val intent = Intent(requireContext(),HelpActivity::class.java)
            startActivity(intent)
        }

        notificationButton.setOnClickListener {
            val intent = Intent(requireContext(),NotificationActivity::class.java)
            startActivity(intent)

        }

        userdataButton.setOnClickListener {
            val intent = Intent(requireContext(),ProfileActivity::class.java)
            startActivity(intent)
        }
    }

}