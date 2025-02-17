package com.example.theweather.ui.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.theweather.MAIN
import com.example.theweather.R
import com.example.theweather.databinding.FragmentHomeBinding
import com.example.theweather.toVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val _viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MAIN.window.statusBarColor = ContextCompat.getColor(MAIN, R.color.mainStatusBarColor)
        MAIN.window.navigationBarColor = ContextCompat.getColor(MAIN,  R.color.navBarColor)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setup()

        return root
    }

    private fun setup() {
        _viewModel.weatherUI.observe(viewLifecycleOwner) {
            binding.temperatureText.text = it.temperatureText
            binding.humidityText.text = it.humidityText
            binding.windSpeedText.text = it.windSpeedText
            binding.feelsLikeText.text = it.feelsText
            binding.cityText.text = it.cityText
            binding.searchEditText.hint = it.searchHintText
            binding.mainImageView.setImageResource(it.main)
            binding.requestTimeText.text = it.requestDateTimeText
            binding.requestTimeText.visibility = it.shouldShowRequestTime.toVisibility()
        }

        binding.searchEditText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                _viewModel.getWeather(binding.searchEditText.text.toString())
            }
            false
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}