package com.example.kreaz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.kreaz.databinding.OffersFragmentBinding
import com.example.kreaz.ui.main.adapter.OffersAdapter
import com.example.kreaz.ui.main.models.OffersViewModel

class OffersFragment : Fragment() {

    private var _binding: OffersFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    private val OffersModel: OffersViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OffersFragmentBinding.inflate(inflater, container, false)
        val root = binding.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val recyclerView = binding.offersRecyclerview
/*
        val itemrecyclerView = item_binding.itemRecyclerView
*/


        recyclerView.setHasFixedSize(true)
        OffersModel.Offers.observe(viewLifecycleOwner, { result ->
            recyclerView.adapter = OffersAdapter(context, result)
        })


    }

}