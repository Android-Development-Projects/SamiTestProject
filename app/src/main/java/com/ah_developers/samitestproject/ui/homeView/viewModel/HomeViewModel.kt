package com.ah_developers.samitestproject.ui.homeView.viewModel

import androidx.lifecycle.ViewModel
import com.ah_developers.samitestproject.ui.homeView.data.CategoriesData

class HomeViewModel : ViewModel() {
    val categoryItems : MutableList<CategoriesData> = mutableListOf()
    val featuredRestaurantItems : MutableList<CategoriesData> = mutableListOf()

    fun addCategoryItems(
        listOfItems : List<CategoriesData>
    ){
        if (categoryItems.isNullOrEmpty()){
            listOfItems.forEach {
                categoryItems.add(it)
            }
        }
    }

    fun addFeaturedRestaurantItems(
        listOfItems : List<CategoriesData>
    ){
        if (featuredRestaurantItems.isNullOrEmpty()){
            listOfItems.forEach {
                featuredRestaurantItems.add(it)
            }
        }
    }

}