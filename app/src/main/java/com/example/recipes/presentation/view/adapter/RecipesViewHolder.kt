package com.example.recipes.presentation.view.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemRecipeBinding
import com.example.recipes.model.RecipesModel
import com.example.recipes.presentation.view.adapter.listener.RecipesListener
import com.squareup.picasso.Picasso

class RecipesViewHolder(
    private val viewBinding: ItemRecipeBinding,
    private val recipesListener: RecipesListener
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(recipesModel: RecipesModel) {
        viewBinding.titleRecipe.text = recipesModel.title
        viewBinding.summaryRecipe.text = recipesModel.summary
        viewBinding.textTime.text = recipesModel.readyInMinutes.toString()
        Picasso.get().load(Uri.parse(recipesModel.image)).into(viewBinding.imageRecipe)

        itemView.setOnClickListener {
            recipesListener.onElementSelected(
                recipesModel.title,
                recipesModel.image,
                recipesModel.summary,
                recipesModel.readyInMinutes,
                recipesModel.aggregateLikes,
                recipesModel.extendedIngredients,
                recipesModel.instructions
            )
        }
    }
}