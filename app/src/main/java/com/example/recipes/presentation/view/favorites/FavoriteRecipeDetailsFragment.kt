package com.example.recipes.presentation.view.favorites

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.recipes.R
import com.example.recipes.databinding.FragmentFavoriteRecipeDetailsBinding
import com.example.recipes.utils.BundleConstants
import com.example.recipes.utils.NavHelper.replaceGraph
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipeDetailsFragment : Fragment() {

    private var _viewBinding: FragmentFavoriteRecipeDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!
    private val viewModel: FavoriteRecipeDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentFavoriteRecipeDetailsBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        bundle?.let { safeBundle ->
            val title = safeBundle.getString(BundleConstants.TITLE)
            val image = safeBundle.getString(BundleConstants.IMAGE)
            val summary = safeBundle.getString(BundleConstants.SUMMARY)
            val readyInMinutes = safeBundle.getString(BundleConstants.READY_IN_MINUTES)
            val aggregateLikes = safeBundle.getString(BundleConstants.AGGREGATE_LIKES)
            val extendedIngredients = safeBundle.getString(BundleConstants.EXTENDED_INGREDIENTS)
            val instructions = safeBundle.getString(BundleConstants.INSTRUCTIONS)

            viewBinding.textFavDetailsTime.text = readyInMinutes
            viewBinding.textFavDetailsAggregateLikes.text = aggregateLikes
            viewBinding.titleFavRecipeDetails.text = title
            viewBinding.summaryFavRecipeDetails.text = summary
            viewBinding.ingredientsFavRecipeDetails.text = extendedIngredients
            viewBinding.instructionsFavRecipeDetails.text = instructions
            Picasso.get().load(Uri.parse(image)).into(viewBinding.imageFavRecipeDetails)

            viewBinding.btnDeleteFavRecipeDetails.setOnClickListener {
                if (title != null) {
                    viewModel.deleteRecipeOnDetails(title)
                }
                viewModel.nav.observe(viewLifecycleOwner) {
                    if (it != null) {
                        val navGraph = findNavController().navInflater.inflate(it)
                        navGraph.startDestination = R.id.recipesFragment
                        replaceGraph(it)
                    }
                }
            }
        }
    }
}