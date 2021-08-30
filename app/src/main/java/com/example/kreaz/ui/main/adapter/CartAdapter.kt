package com.example.kreaz.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.kreaz.R
import com.example.kreaz.data.cart
import com.example.kreaz.ui.main.Orders
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CartAdapter(private val clickListener: Orders) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    val cartset: ArrayList<cart> = arrayListOf()


    fun swapData(data: List<cart>) {
        cartset.clear()
        cartset.addAll(data)
        notifyDataSetChanged()
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
            clickListener.deleteThisId(cartset[position].id)
        }

    }
}