package com.example.theweather.ui.clothes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theweather.R
import com.example.theweather.ui.clothes.models.ClothesTypeUI

class ClothesTypesRecyclerAdapter (private val types: List<ClothesTypeUI>, private val onItemClick: (ClothesTypeUI) -> Unit) : RecyclerView
.Adapter<ClothesTypesRecyclerAdapter.ClothesTypesViewHolder>() {

    class ClothesTypesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.tvHeading)
        private val imageView: ImageView = itemView.findViewById(R.id.title_image)

        fun bind(item: ClothesTypeUI, onItemClick: (ClothesTypeUI) -> Unit){
            itemView.setOnClickListener{ onItemClick(item)}
            titleTextView.text = item.title
            imageView.setImageResource(item.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothesTypesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ClothesTypesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClothesTypesViewHolder, position: Int) {
        holder.bind(types[position], onItemClick)
    }

    override fun getItemCount() = types.size
}