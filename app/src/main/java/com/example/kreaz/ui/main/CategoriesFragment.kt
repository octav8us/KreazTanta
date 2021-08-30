package com.example.kreaz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kreaz.databinding.CategoriesFragmentBinding
import com.example.kreaz.ui.main.adapter.CategoriesAdapter
import com.example.kreaz.ui.main.models.CategoriesModel

/**
 * A placeholder fragment containing a simple view.
 */

class CategoriesFragment : Fragment() {
    private var _binding: CategoriesFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView


    private val categoriesModel: CategoriesModel by activityViewModels {
        CategoriesModel.ItemsViewModelFactory(
            (activity?.application as com.example.kreaz.kreazApplication).database
                .cartDao()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CategoriesFragmentBinding.inflate(inflater, container, false)
        val root = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val recyclerView = binding.categoriesRecyclerView
        binding.goToCart.setOnClickListener {
            findNavController().navigate(
                CategoriesFragmentDirections.actionCategoriesFragmentToCartFragment()
            )

        }
        recyclerView.setHasFixedSize(true)

        categoriesModel.categories.observe(viewLifecycleOwner, { result ->

            recyclerView.adapter = CategoriesAdapter(
                context, result, recyclerView, this
            )
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
/*
        categoriesModel.Clean()
*/
        _binding = null
    }

    var orderId = 0
    var orderPrice = "i"
    var orderName = "i"


}