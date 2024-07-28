package com.example.theweather

import android.view.View

lateinit var MAIN: MainActivity
fun Boolean.toVisibility():Int {
return if (this){
    View.VISIBLE
    }else{
    View.GONE
    }
}