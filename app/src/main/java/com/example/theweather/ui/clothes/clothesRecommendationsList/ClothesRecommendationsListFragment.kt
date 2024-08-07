package com.example.theweather.ui.clothes.clothesRecommendationsList

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theweather.R
import com.example.theweather.databinding.FragmentClothesBinding
import com.example.theweather.databinding.FragmentClothesRecommendationsBinding
import com.example.theweather.databinding.FragmentClothesRecommendationsListBinding
import com.example.theweather.ui.clothes.ClothesTypesRecyclerAdapter
import com.example.theweather.ui.clothes.clothesRecommendations.ClothesRecommendationsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClothesRecommendationsListFragment : Fragment() {

    private var _binding: FragmentClothesRecommendationsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val _viewModel: ClothesRecommendationsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClothesRecommendationsListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.clothesRecommendationsRecycle.layoutManager = LinearLayoutManager(context)
        _viewModel.clothesList.observe(viewLifecycleOwner){
            binding.clothesRecommendationsRecycle.adapter = ClothesRecommendationRecyclerAdapter(it){ item ->
                {}

            }
        }
        return root
    }
}