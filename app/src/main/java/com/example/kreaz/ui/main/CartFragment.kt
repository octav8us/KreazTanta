package com.example.kreaz.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kreaz.databinding.CartFragmentBinding
import com.example.kreaz.ui.main.adapter.CartAdapter
import com.example.kreaz.ui.main.models.CartModel

class CartFragment : Fragment(), Orders {

    private val cartModel: CartModel by activityViewModels {
        CartModel.ItemsViewModelFactory(
            (activity?.application as com.example.kreaz.kreazApplication).database
                .cartDao()
        )
    }

    private var _binding: CartFragmentBinding? = null
    private val binding get() = _binding!!
    private val adapter = CartAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = CartFragmentBinding.inflate(inflater, container, false)
        val root = binding.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        this@CartFragment.binding.cartRecyclerview.adapter = this@CartFragment.adapter

        cartModel.total.observe(viewLifecycleOwner, { result ->
            binding.totalCart.text = result.toString()

        })


        cartModel.cartItems.observe(viewLifecycleOwner, { result ->
            Log.d("listItems", "bb $result")


            adapter.swapData(result ?: listOf())


        })
        binding.OrderButton.setOnClickListener {
            val alertDialog: AlertDialog? = activity?.let {
                val builder = AlertDialog.Builder(it)
                    .setTitle("confirm")
                    .setMessage("Are you sure you want to buy?")
                builder.apply {
                    setPositiveButton("yes",
                        DialogInterface.OnClickListener { dialog, id ->

                            cartModel.SendOrder(cartModel.cartItems.value!!)
                            cartModel.Response.observe(viewLifecycleOwner) {
                                if (it != null) {
                                    Toast.makeText(context, it.type, Toast.LENGTH_LONG).show()
                                    cartModel.Clean()
                                }

                            }

                            dialog.dismiss()

                        })
                    setNegativeButton("cancel",
                        DialogInterface.OnClickListener { dialog, id ->

                            dialog.dismiss()
                        })
                }
                builder.create()
            }

            var ConfirmationDialouge = alertDialog
            ConfirmationDialouge?.show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun deleteThisId(deleteThisId: Int) {
        cartModel.deleteItem(deleteThisId)
    }


}




