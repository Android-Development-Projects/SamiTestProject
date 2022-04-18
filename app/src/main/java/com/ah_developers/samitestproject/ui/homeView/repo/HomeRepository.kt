package com.ah_developers.samitestproject.ui.homeView.repo

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.ah_developers.samitestproject.R
import com.ah_developers.samitestproject.ui.homeView.data.CategoriesData

class HomeRepository(
    private val context: Context
) {
    @RequiresApi(Build.VERSION_CODES.M)
    fun addCategories(): List<CategoriesData> {
        return listOf(
            CategoriesData(
                getDrawable(context, R.drawable.pizza_slice),
                context.getColor(R.color.pink_50),
                context.getString(R.string.italian)
            ),
            CategoriesData(
                getDrawable(context, R.drawable.lebanese),
                context.getColor(R.color.blue_50),
                context.getString(R.string.lebanese)
            ),
            CategoriesData(
                getDrawable(context, R.drawable.ic_pizza_slice),
                context.getColor(R.color.pink_50),
                context.getString(R.string.chinese)
            )
        )
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun addFeaturedRestaurants(): List<CategoriesData> {
        return listOf(
            CategoriesData(
                getDrawable(context, R.drawable.restaurant),
                context.getColor(R.color.white),
                context.getString(R.string.piccola_cucina)
            ),
            CategoriesData(
                getDrawable(context, R.drawable.restaurant_two),
                context.getColor(R.color.white),
                context.getString(R.string.empire_streak_hotel)
            ),
            CategoriesData(
                getDrawable(context, R.drawable.restaurant),
                context.getColor(R.color.white),
                context.getString(R.string.piccola_cucina)
            )
        )
    }

}