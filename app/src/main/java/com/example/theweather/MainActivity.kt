package com.example.theweather


import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.theweather.databinding.ActivityMainBinding
import com.example.weatherdata.weather.models.MainEnum
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    var currentTemp: Double = 0.0
    var currentMainEnum: MainEnum = MainEnum.RAIN
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MAIN = this
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navView= binding.navView

        navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setupWithNavController(navController)
    }

    public fun hideBottomNavigation(){
        navView.visibility = View.GONE
    }
    public fun showBottomNavigation(){
        navView.visibility = View.VISIBLE
    }
}