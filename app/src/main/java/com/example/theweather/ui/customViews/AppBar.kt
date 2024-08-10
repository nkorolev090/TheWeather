package com.example.theweather.ui.customViews

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.getResourceIdOrThrow
import com.example.theweather.R

class AppBar(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var leftImage: Int
    private var rightImage: Int
    private var titleText: String?

    private var titleTextView: TextView
    private var rightImageView: ImageView
    private var leftImageView: ImageView

    init {
        View.inflate(context, R.layout.app_bar_layout, this@AppBar)
        titleTextView = findViewById<TextView>(R.id.titleTextView)
        rightImageView = findViewById<ImageView>(R.id.rightImage)
        leftImageView = findViewById<ImageView>(R.id.leftImage)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.AppBar,
            0,
            0).apply {
            try {
                leftImage = getResourceIdOrThrow(R.styleable.AppBar_leftIcon)
                rightImage = getResourceIdOrThrow(R.styleable.AppBar_rightIcon)
                titleText = getString(R.styleable.AppBar_titleText)

                titleTextView.text = titleText
                rightImageView.setImageResource(rightImage)
                leftImageView.setImageResource(leftImage)
            }finally {
                recycle()
            }
        }
    }

    fun setLeftOnClickListener(onClickListener: OnClickListener){
        leftImageView.setOnClickListener(onClickListener)
    }

    fun setRightOnClickListener(onClickListener: OnClickListener){
        rightImageView.setOnClickListener(onClickListener)
    }

    fun setTitleText(text: String){
        titleTextView.text = text
    }
}