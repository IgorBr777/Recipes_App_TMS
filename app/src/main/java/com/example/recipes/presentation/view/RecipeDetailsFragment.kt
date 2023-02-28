package com.example.recipes.presentation.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipes.R
import com.example.recipes.databinding.FragmentRecipeDetailsBinding
import com.example.recipes.utils.BundleConstants
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private var _viewBinding: FragmentRecipeDetailsBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: RecipeDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _viewBinding = FragmentRecipeDetailsBinding.inflate(inflater)
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

            viewBinding.textDetailsTime.text = readyInMinutes
            viewBinding.textDetailsAggregateLikes.text =aggregateLikes
            viewBinding.titleRecipeDetails.text = title
            viewBinding.summaryRecipeDetails.text = summary
            viewBinding.ingredientsRecipeDetails.text = extendedIngredients
            viewBinding.instructionsRecipeDetails.text = instructions

            Picasso.get().load(Uri.parse(image)).into(viewBinding.imageRecipeDetails)

        }



    }


}




