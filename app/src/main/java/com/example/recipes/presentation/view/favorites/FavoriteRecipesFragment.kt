package com.example.recipes.presentation.view.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.FragmentFavoriteRecipesBinding
import com.example.recipes.presentation.view.favorites.adapter.FavoriteRecipesAdapter
import com.example.recipes.presentation.view.favorites.adapter.listener.FavoriteRecipesListener
import com.example.recipes.utils.BundleConstants
import com.example.recipes.utils.NavHelper.navigateWithBundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment(), FavoriteRecipesListener {

    private var _viewBinding: FragmentFavoriteRecipesBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var favRecipesAdapter: FavoriteRecipesAdapter

    private val viewModel: FavoriteRecipesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _viewBinding = FragmentFavoriteRecipesBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favRecipesAdapter = FavoriteRecipesAdapter(this)
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(context)
        viewBinding.recyclerView.adapter = favRecipesAdapter
        viewModel.getFavoriteRecipes()
        viewModel.favorites.observe(viewLifecycleOwner) {
            favRecipesAdapter.submitList(it)

        }
        viewModel.favbundle.observe(viewLifecycleOwner) { navBundle ->
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

    }

    override fun onFavoriteElementSelected(
        title: String,
        image: String,
        summary: String,
        readyInMinutes: Int,
        aggregateLikes: Int,
        extendedIngredients: String,
        instructions: String
    ) {
        viewModel.favoriteElementClicked(
            title,
            image,
            summary,
            readyInMinutes,
            aggregateLikes,
            extendedIngredients,
            instructions
        )

    }

    override fun onFavDeleteClicked(title: String) {
        viewModel.deleteRecipe(title)
    }


}