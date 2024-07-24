package com.example.theweather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.theweather.databinding.FragmentHomeBinding
import com.example.theweather.view_models.home.HomeViewModel
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


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val temperatureTextView: TextView = binding.temperatureText
        _viewModel.temperatureText.observe(viewLifecycleOwner) {
            temperatureTextView.text =it
        }
        val humidityTextView: TextView = binding.humidityText
        _viewModel.humidityText.observe(viewLifecycleOwner) {
            humidityTextView.text =it
        }
        val windSpeedTextView: TextView = binding.windSpeedText
        _viewModel.windSpeedText.observe(viewLifecycleOwner) {
            windSpeedTextView.text =it
        }
        val feelsLikeTextView: TextView = binding.feelsLikeText
        _viewModel.feelsText.observe(viewLifecycleOwner) {
            feelsLikeTextView.text =it
        }
        val cityTextView: TextView = binding.cityText
        _viewModel.searchCityText.observe(viewLifecycleOwner) {
            cityTextView.text =it
        }
        val searchEditText: EditText = binding.searchEditText
        _viewModel.searchHintText.observe(viewLifecycleOwner){
            searchEditText.hint = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}