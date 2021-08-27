package com.example.kreaz.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.kreaz.R
import com.example.kreaz.data.cart
import com.example.kreaz.databinding.CartFragmentBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CartFragment : Fragment(), Orders {

    private val categoriesModel: CategoriesModel by activityViewModels {
        CategoriesModel.ItemsViewModelFactory(
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

        categoriesModel.total.observe(viewLifecycleOwner, { result ->
            binding.totalCart.text = result.toString()

        })
        this@CartFragment.binding.cartRecyclerview.adapter = this@CartFragment.adapter

        categoriesModel.cartItems.observe(viewLifecycleOwner, { result ->
            Log.d("listItems", "bb $result")


            adapter.swapData(result ?: listOf())


        })


    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    override fun deleteThisId(deleteThisId: Int) {
        categoriesModel.deleteItem(deleteThisId)
        categoriesModel.refreshCart()
    }


}

class CartAdapter(CartFragment: Orders) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    var lisner: Orders = CartFragment
    private val cartset: ArrayList<cart> = arrayListOf()
    fun delete(id: Int) {
        lisner.deleteThisId(id)

    }

    fun swapData(data: List<cart>) {
        cartset.clear()
        cartset.addAll(data)
        notifyItemRangeChanged(0, itemCount - 1)

    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView = itemView.findViewById(R.id.cartName)
        var itemQuantity: TextView = itemView.findViewById(R.id.cartQuantity)
        var deleteButton: FloatingActionButton = itemView.findViewById(R.id.deleteItem)

        var card: CardView = itemView.findViewById(R.id.cartCardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_card, parent, false)
        return ViewHolder(adapterLayout)

    }

    override fun getItemCount(): Int = (cartset.size)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = cartset[position].itemName
        holder.itemQuantity.text = cartset[position].quantity.toString()
        holder.deleteButton.setOnClickListener {
            delete(cartset[position].id)

        }

    }
}


