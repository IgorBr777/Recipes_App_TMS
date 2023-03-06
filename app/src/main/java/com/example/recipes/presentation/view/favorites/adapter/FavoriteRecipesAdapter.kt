package com.example.recipes.presentation.view.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemFavoriteRecipeBinding
import com.example.recipes.model.FavoriteRecipesModel
import com.example.recipes.presentation.view.favorites.adapter.listener.FavoriteRecipesListener

class FavoriteRecipesAdapter
    (private val favoriteRecipesListener: FavoriteRecipesListener) :
    RecyclerView.Adapter<FavoriteRecipesViewHolder>() {

    private var listRecipes = mutableListOf<FavoriteRecipesModel>()


    fun submitList(list: List<FavoriteRecipesModel>) {
        this.listRecipes.clear()
        this.listRecipes = list.toMutableList()
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipesViewHolder {
        val viewBinding = ItemFavoriteRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FavoriteRecipesViewHolder(viewBinding, favoriteRecipesListener)


    }

    override fun onBindViewHolder(holder: FavoriteRecipesViewHolder, position: Int) {

        holder.bind(listRecipes[position])


    }

    override fun getItemCount(): Int {

        return listRecipes.size

    }


}