package com.example.recipes.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemRecipeBinding
import com.example.recipes.model.RecipesModel
import com.example.recipes.presentation.view.adapter.listener.RecipesListener

class RecipesAdapter(
    private val recipesListener: RecipesListener
) : RecyclerView.Adapter<RecipesViewHolder>() {

    private var listRecipes = mutableListOf<RecipesModel>()

    fun submitList(list: List<RecipesModel>) {
        this.listRecipes.clear()
        this.listRecipes = list.toMutableList()
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val viewBinding = ItemRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RecipesViewHolder(viewBinding, recipesListener)

    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(listRecipes[position])
    }

    override fun getItemCount(): Int {
        return listRecipes.size
    }


}