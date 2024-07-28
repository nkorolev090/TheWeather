package com.example.theweather.ui.clothes.clothesRecommendations

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.theweather.R
import com.example.theweather.databinding.FragmentClothesBinding
import com.example.theweather.databinding.FragmentClothesRecommendationsBinding

class ClothesRecommendationsFragment : Fragment() {

    private var _binding: FragmentClothesRecommendationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val _viewModel: ClothesRecommendationsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClothesRecommendationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonBack.setOnClickListener{
            _viewModel.navToClothesFragment()
        }

        binding.buttonBack.text = _viewModel.backBtnText

        return root
    }
}