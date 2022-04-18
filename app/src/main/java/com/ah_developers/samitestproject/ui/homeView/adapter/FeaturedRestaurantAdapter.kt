package com.ah_developers.samitestproject.ui.homeView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ah_developers.samitestproject.databinding.ItemsFeaturedRestaurantsBinding
import com.ah_developers.samitestproject.ui.homeView.data.CategoriesData

class FeaturedRestaurantAdapter(
    private val itemList: List<CategoriesData>,
) : RecyclerView.Adapter<FeaturedRestaurantAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ItemsFeaturedRestaurantsBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        binding = ItemsFeaturedRestaurantsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.binding.apply {
            ivRestaurant.setImageDrawable(currentItem.categoryImage)
            cvFeaturedRestaurants.setCardBackgroundColor(currentItem.backgroundColor)
            tvRestaurantName.text = currentItem.categoryName
        }
    }

    override fun getItemCount(): Int = itemList.size
}