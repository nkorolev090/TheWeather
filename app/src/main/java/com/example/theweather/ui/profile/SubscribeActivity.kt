package com.example.theweather.ui.profile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.theweather.R
import com.razorpay.Checkout


class SubscribeActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_subscribe)

        Checkout.preload(applicationContext)
        val co = Checkout()
        // apart from setting it in AndroidManifest.xml, keyId can also be set
        // programmatically during runtime
        co.setKeyID("rzp_live_XXXXXXXXXXXXXX")


        val title: TextView = findViewById(R.id.item_list_title_one)
        val text:TextView = findViewById(R.id.item_list_text)

        title.text=intent.getStringExtra("itemTitle")
        text.text=intent.getStringExtra("itemText")



    }
}