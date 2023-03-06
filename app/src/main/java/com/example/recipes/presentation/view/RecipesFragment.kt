package com.example.recipes.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.FragmentRecipesBinding
import com.example.recipes.presentation.view.adapter.RecipesAdapter
import com.example.recipes.presentation.view.adapter.listener.RecipesListener
import com.example.recipes.utils.BundleConstants
import com.example.recipes.utils.NavHelper.navigateWithBundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment(), RecipesListener {

    private var _viewBinding: FragmentRecipesBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var recipesAdapter: RecipesAdapter

    private val viewModel: RecipesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentRecipesBinding.inflate(inflater)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipesAdapter = RecipesAdapter(this)
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerView.adapter = recipesAdapter
        viewModel.getRecipes()
        viewModel.recipes.observe(viewLifecycleOwner) { listRecipes ->
            recipesAdapter.submitList(listRecipes)

        }

        viewModel.bundle.observe(viewLifecycleOwner) { navBundle ->
            if (navBundle != null) {
                val bundle = Bundle()
                bundle.putString(BundleConstants.TITLE, navBundle.title)
                bundle.putString(BundleConstants.IMAGE, navBundle.image)
                bundle.putString(
                    BundleConstants.READY_IN_MINUTES,
                    navBundle.readyInMinutes.toString()
                )
                bundle.putString(
                    BundleConstants.AGGREGATE_LIKES,
                    navBundle.aggregateLikes.toString()
                )

                bundle.putString(BundleConstants.SUMMARY, navBundle.summary)
                bundle.putString(
                    BundleConstants.EXTENDED_INGREDIENTS,
                    navBundle.extendedIngredients
                )
                bundle.putString(BundleConstants.INSTRUCTIONS, navBundle.instructions)

                navigateWithBundle(navBundle.destinationId, bundle)



                viewModel.userNavigated()
            }


        }

        viewBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recipesAdapter.filter.filter(newText)
                viewModel.findRecipe(newText ?: "")
                return true

            }

        })


        viewModel.findRecipe.observe(viewLifecycleOwner) { listRecipes ->
            recipesAdapter.submitList(listRecipes)

        }


    }


    override fun onElementSelected(
        title: String,
        image: String,
        summary: String,
        readyInMinutes: Int,
        aggregateLikes: Int,
        extendedIngredients: String,
        instructions: String

    ) {
        viewModel.elementClicked(
            title,
            image,
            summary,
            readyInMinutes,
            aggregateLikes,
            extendedIngredients,
            instructions
        )

    }

}
