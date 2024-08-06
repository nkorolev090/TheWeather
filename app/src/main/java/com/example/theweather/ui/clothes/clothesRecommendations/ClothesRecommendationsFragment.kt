package com.example.theweather.ui.clothes.clothesRecommendations

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.theweather.MAIN
import com.example.theweather.R
import com.example.theweather.databinding.FragmentClothesRecommendationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        MAIN.hideBottomNavigation()
        MAIN.window.statusBarColor = ContextCompat.getColor(MAIN, R.color.clothesRecommendationsColor)
        MAIN.window.navigationBarColor = ContextCompat.getColor(MAIN,  R.color.white)

        _binding = FragmentClothesRecommendationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonBack.setOnClickListener{
            _viewModel.navToClothesFragment()
        }

        binding.buttonBack.text = _viewModel.backBtnText
        binding.textViewStyle.text = _viewModel.styleText
        binding.textViewSeason.text = _viewModel.seasonText
        binding.textViewMaterial.text = _viewModel.styleText

        _viewModel.currentClothes.observe(viewLifecycleOwner){
            binding.textViewMaterialValue.text = it.materialText
            binding.textViewName.text = it.nameText
            binding.textViewSeasonValue.text = it.seasonText
            binding.textViewStyleValue.text = it.styleText
        }

        return root
    }
}