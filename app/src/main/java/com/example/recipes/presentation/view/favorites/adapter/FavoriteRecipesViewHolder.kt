package com.example.recipes.presentation.view.favorites.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemFavoriteRecipeBinding
import com.example.recipes.model.FavoriteRecipesModel
import com.example.recipes.presentation.view.favorites.adapter.listener.FavoriteRecipesListener
import com.squareup.picasso.Picasso

class FavoriteRecipesViewHolder(
    private val viewBinding: ItemFavoriteRecipeBinding,
    private val favoriteRecipesListener: FavoriteRecipesListener

): RecyclerView.ViewHolder(viewBinding.root) {

fun bind(favoriteRecipesModel: FavoriteRecipesModel){
    viewBinding.titleFavRecipe.text=favoriteRecipesModel.title
    viewBinding.summaryFavRecipe.text = favoriteRecipesModel.summary
    viewBinding.textFavTime.text = favoriteRecipesModel.readyInMinutes.toString()
    Picasso.get().load(Uri.parse(favoriteRecipesModel.image)).into(viewBinding.imageFavRecipe)

    itemView.setOnClickListener {
        favoriteRecipesListener.onFavoriteElementSelected(
            favoriteRecipesModel.title,
            favoriteRecipesModel.image,
            favoriteRecipesModel.summary,
            favoriteRecipesModel.readyInMinutes,
            favoriteRecipesModel.aggregateLikes,
            favoriteRecipesModel.extendedIngredients,
            favoriteRecipesModel.instructions
        )
    }

viewBinding.btnDeleteFavRecipe.setOnClickListener {
    favoriteRecipesListener.onFavDeleteClicked(favoriteRecipesModel.title)
}
}
}