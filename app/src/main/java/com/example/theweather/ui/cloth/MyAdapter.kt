package com.example.theweather.ui.cloth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theweather.R
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(
    private var imageId: Array<Int>,
    private var heading: Array<String>,
    private var news: Array<String>
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var expandedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleImage.setImageResource(imageId[position])
        holder.tvHeading.text = heading[position]
        holder.tvNewsText.text = news[position]

        val isExpanded = position == expandedPosition
        holder.tvNewsText.maxLines = if (isExpanded) Int.MAX_VALUE else 3
        holder.btnShowMore.text = if (isExpanded) "Показать меньше" else "Показать больше"

        holder.btnShowMore.setOnClickListener {
            expandedPosition = if (isExpanded) -1 else position
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return imageId.size
    }

    fun updateData(newImageId: Array<Int>, newHeading: Array<String>, newNews: Array<String>) {
        imageId = newImageId
        heading = newHeading
        news = newNews
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        val tvNewsText: TextView = itemView.findViewById(R.id.tvNewsText)
        val btnShowMore: Button = itemView.findViewById(R.id.btnShowMore)
    }
}