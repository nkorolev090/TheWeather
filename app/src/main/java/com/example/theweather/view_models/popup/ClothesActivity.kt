package com.example.theweather.view_models.popup

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.theweather.R
import com.google.android.material.bottomsheet.BottomSheetDialog


//popup
class ClothesActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clothes)

        val bottom_sheet: Button = findViewById(R.id.bottom_sheet)
        bottom_sheet.setOnClickListener {
            val view:View=layoutInflater.inflate(R.layout.bottomsheetlayout,null)

            val dialog=BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()
        }
    }


}