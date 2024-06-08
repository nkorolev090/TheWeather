package com.example.theweather.ui.recommendations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.theweather.databinding.FragmentRecommendationsBinding
import com.example.theweather.view_models.recommendations.RecommendationsViewModel

class RecommendationsFragment : Fragment() {

    private var _binding: FragmentRecommendationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recommendationsViewModel =
            ViewModelProvider(this).get(RecommendationsViewModel::class.java)

        _binding = FragmentRecommendationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        recommendationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}