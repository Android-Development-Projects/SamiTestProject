package com.ah_developers.samitestproject.ui.homeView.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.ah_developers.samitestproject.R
import com.ah_developers.samitestproject.databinding.FragmentHomeBinding
import com.ah_developers.samitestproject.ui.homeView.adapter.CategoryAdapter
import com.ah_developers.samitestproject.ui.homeView.adapter.FeaturedRestaurantAdapter
import com.ah_developers.samitestproject.ui.homeView.repo.HomeRepository
import com.ah_developers.samitestproject.ui.homeView.viewModel.HomeViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeRepository: HomeRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.searchView.queryHint = getString(R.string.search_query_hint)
        binding.bottomNavigationView.background = null
        binding.btnSeeAllCategories.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_600))
        binding.btnSeeAllFeaturedRestaurant.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_600))
        homeRepository = HomeRepository(requireContext())
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        // Inflate the layout for this fragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            setCategories()
            setFeaturedRestaurants()
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setCategories() {
        homeViewModel.addCategoryItems(homeRepository.addCategories())
        val items = homeViewModel.categoryItems
        val categoriesAdapter = CategoryAdapter(items)
        binding.recyclerViewCategories.apply {
            adapter = categoriesAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setFeaturedRestaurants() {
        homeViewModel.addFeaturedRestaurantItems(homeRepository.addFeaturedRestaurants())
        val items = homeViewModel.featuredRestaurantItems
        val featuredRestaurantAdapter = FeaturedRestaurantAdapter(items)
        binding.recyclerViewFeaturedRestaurant.apply {
            adapter = featuredRestaurantAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
        }
    }

}