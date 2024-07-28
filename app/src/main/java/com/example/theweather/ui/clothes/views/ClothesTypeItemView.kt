package com.example.theweather.ui.clothes.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.getResourceIdOrThrow
import com.example.theweather.R

class ClothesTypeItemView(
    context: Context,
    attributeSet: AttributeSet) : ConstraintLayout(context, attributeSet){
    private var itemImage: Int
    private var itemText: String?

    init {
        View.inflate(context, R.layout.li_clothes_type_item, this@ClothesTypeItemView)
        val textView = findViewById<TextView>(R.id.text_title)
        val imageView = findViewById<ImageView>(R.id.imageModel)
            context.theme.obtainStyledAttributes(
                attributeSet,
                R.styleable.ClothesTypeItemView,
                0,
                0).apply {
                    try {
                        itemText = getString(R.styleable.ClothesTypeItemView_itemText)
                        itemImage = getResourceIdOrThrow(R.styleable.ClothesTypeItemView_itemImageSrc)

                        textView.text = itemText
                        imageView.setImageResource(itemImage)
                    }finally {
                        recycle()
                    }
            }
        }
}