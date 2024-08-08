package com.example.theweather.ui.clothes.clothesRecommendations

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.example.theweather.MAIN
import com.example.theweather.R
import com.example.theweather.databinding.FragmentClothesRecommendationsBinding
import com.example.theweather.ui.clothes.models.ClothesUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClothesRecommendationsFragment : Fragment() {

    private var _binding: FragmentClothesRecommendationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val _viewModel: ClothesRecommendationsViewModel by viewModels()

    @RequiresApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        MAIN.hideBottomNavigation()
        MAIN.window.statusBarColor = ContextCompat.getColor(MAIN, R.color.clothesRecommendationsColor)
        MAIN.window.navigationBarColor = ContextCompat.getColor(MAIN,  R.color.white)

        val value = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getSerializable("clothes", ClothesUI::class.java)
        } else {
            TODO("VERSION.SDK_INT < TIRAMISU")
        };

        if(value != null){
            _viewModel.currentClothesUI = value
        }
        _binding = FragmentClothesRecommendationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        binding.buttonBack.setOnClickListener{
//            _viewModel.navToClothesFragment()
//        }

        binding.backBtn.text = _viewModel.backBtnText
        binding.textViewStyle.text = _viewModel.styleText
        binding.textViewSeason.text = _viewModel.seasonText
        binding.textViewMaterial.text = _viewModel.materialText

        //_viewModel.currentClothes.observe(viewLifecycleOwner){
            binding.textViewMaterialValue.text = _viewModel.currentClothesUI.materialText
            binding.textViewName.text = _viewModel.currentClothesUI.nameText
            binding.textViewSeasonValue.text = _viewModel.currentClothesUI.seasonText
            binding.textViewStyleValue.text = _viewModel.currentClothesUI.styleText
        //}

        return root
    }
}