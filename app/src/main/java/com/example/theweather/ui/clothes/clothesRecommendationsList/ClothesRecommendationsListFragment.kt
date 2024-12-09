//package com.example.theweather.ui.clothes.clothesRecommendationsList
//
//import androidx.fragment.app.viewModels
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.core.content.ContextCompat
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.clothesdb.models.enums.MainTypeEnumDBO
//import com.example.theweather.MAIN
//import com.example.theweather.R
//import com.example.theweather.databinding.FragmentClothesBinding
//import com.example.theweather.databinding.FragmentClothesRecommendationsBinding
//import com.example.theweather.databinding.FragmentClothesRecommendationsListBinding
//import com.example.theweather.ui.clothes.ClothesTypesRecyclerAdapter
//import com.example.theweather.ui.clothes.clothesRecommendations.ClothesRecommendationsViewModel
//import com.example.theweather.ui.clothes.models.ClothesTypeUI
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ClothesRecommendationsListFragment : Fragment() {
//
//    private var _binding: FragmentClothesRecommendationsListBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    private val _viewModel: ClothesRecommendationsListViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        MAIN.hideBottomNavigation()
//        MAIN.window.statusBarColor = ContextCompat.getColor(MAIN, R.color.white)
//        MAIN.window.navigationBarColor = ContextCompat.getColor(MAIN,  R.color.white)
//
//        val parameter = requireArguments().getString("clothesType")
//        _viewModel.clothesType = parameter.toMainType()
//        _viewModel.titleText = parameter.toTitleText()
//        _viewModel.loadClothesList()
//
//        _binding = FragmentClothesRecommendationsListBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        binding.clothesRecommendationsRecycle.layoutManager = LinearLayoutManager(context)
//        _viewModel.clothesList.observe(viewLifecycleOwner){
//            binding.clothesRecommendationsRecycle.adapter = ClothesRecommendationRecyclerAdapter(it){ item ->
//                _viewModel.navToClothesRecommendationsDetails(item)
//            }
//        }
//
//        binding.appBar.setTitleText(_viewModel.titleText)
//        binding.appBar.setLeftOnClickListener(View.OnClickListener { _viewModel.navToClothes() })
//
//        return root
//    }
//}
//
//public fun String?.toMainType(): MainTypeEnumDBO {
//return when(this){
//    "HIGH" -> MainTypeEnumDBO.HIGH
//    "LOW" -> MainTypeEnumDBO.LOW
//    "SHOES" -> MainTypeEnumDBO.SHOES
//    else -> throw Exception()
//}
//
//}
//
//public fun String?.toTitleText(): String{
//    return when(this){
//        "LOW" -> "Одежда: Низ"
//        "HIGH" -> "Одежда: Верх"
//        "SHOES" -> "Обувь"
//        else -> "-"
//    }
//}