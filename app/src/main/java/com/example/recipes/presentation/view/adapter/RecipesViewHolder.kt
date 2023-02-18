package com.example.recipes.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemRecipeBinding
import com.example.recipes.model.RecipesModel
import com.example.recipes.presentation.view.adapter.listener.RecipesListener

class RecipesViewHolder(
    private val viewBinding: ItemRecipeBinding,
    private val recipesListener: RecipesListener
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(recipesModel: RecipesModel) {


    }


}