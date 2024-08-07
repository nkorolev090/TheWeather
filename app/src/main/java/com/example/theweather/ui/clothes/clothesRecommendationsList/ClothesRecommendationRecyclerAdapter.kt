package com.example.theweather.ui.clothes.clothesRecommendationsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theweather.R
import com.example.theweather.ui.clothes.models.ClothesUI

class ClothesRecommendationRecyclerAdapter (private val clothes: List<ClothesUI>, private val onItemClick: (ClothesUI) -> Unit) : RecyclerView
.Adapter<ClothesRecommendationRecyclerAdapter.ClothesRecommendationViewHolder>() {

    class ClothesRecommendationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameText)
        private val styleTextView: TextView = itemView.findViewById(R.id.styleText)
        private val imageView: ImageView = itemView.findViewById(R.id.imageModel)


        fun bind(item: ClothesUI, onItemClick: (ClothesUI) -> Unit){
            itemView.setOnClickListener{ onItemClick(item)}
            nameTextView.text = item.nameText
            styleTextView.text = item.styleText
            imageView.setImageResource(R.drawable.jacket)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesRecommendationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.li_clothes_item, parent, false)
        return ClothesRecommendationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClothesRecommendationViewHolder, position: Int) {
        holder.bind(clothes[position], onItemClick)
    }

    override fun getItemCount() = clothes.size
}