package com.example.recipes.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.ItemRecipeBinding
import com.example.recipes.model.RecipesModel
import com.example.recipes.presentation.view.adapter.listener.RecipesListener
import java.util.*

class RecipesAdapter(
    private val recipesListener: RecipesListener
) : RecyclerView.Adapter<RecipesViewHolder>(), Filterable {
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

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<RecipesModel>()

                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(listRecipes)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()

                    for (item in listRecipes) {
                        if (item.title.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            filteredList.add(item)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList

                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listRecipes.clear()
                listRecipes.addAll(results?.values as List<RecipesModel>)
                notifyDataSetChanged()
            }

        }
    }
}








