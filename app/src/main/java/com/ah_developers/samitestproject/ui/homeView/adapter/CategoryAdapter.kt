package com.ah_developers.samitestproject.ui.homeView.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ah_developers.samitestproject.databinding.ItemsCategoriesBinding
import com.ah_developers.samitestproject.ui.homeView.data.CategoriesData

class CategoryAdapter(
   private val itemList : List<CategoriesData>
)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
    class ViewHolder(
        val binding: ItemsCategoriesBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        binding = ItemsCategoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.binding.apply {
            ivCategory.setImageDrawable(currentItem.categoryImage)
            cvCategory.setCardBackgroundColor(currentItem.backgroundColor)
            tvCategoryName.text = currentItem.categoryName
        }
    }

    override fun getItemCount(): Int = itemList.size
}