package com.example.theweather.ui.clothes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theweather.databinding.FragmentClothesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClothesFragment : Fragment() {

    private var _binding: FragmentClothesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val _viewModel: ClothesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClothesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.clothesTypesRecycle.layoutManager = LinearLayoutManager(context)

        _viewModel.clothesTypes.observe(viewLifecycleOwner){
            binding.clothesTypesRecycle.adapter = ClothesTypesRecyclerAdapter(it){ item ->
                _viewModel.navToClothesRecommendations(item)
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}