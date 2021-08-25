package com.example.kreaz.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kreaz.R
import com.example.kreaz.network.Item
import android.text.method.ScrollingMovementMethod
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.kreaz.ui.main.AddToCartDialoge
import com.example.kreaz.ui.main.CategoriesFragment
import com.example.kreaz.ui.main.CategoriesFragmentDirections
import com.example.kreaz.ui.main.Communicator
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ItemsAdapter(
    private val context: Context?, private val dataset: List<Item>?, var manger: CategoriesFragment
) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemPrice: TextView
        var itemDetail: TextView
        var card: CardView
        var addToCart: FloatingActionButton

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemPrice = itemView.findViewById(R.id.itemPrice)
            itemDetail = itemView.findViewById(R.id.itemDetail)
            card = itemView.findViewById(R.id.card_view)
            addToCart = itemView.findViewById(R.id.addToCart)

        }

        override fun onClick(v: View?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.items_card, parent, false)
        return ViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        if (dataset != null) {
            holder.itemTitle.text = dataset.get(position).name ?: "loading!!"
            holder.itemDetail.text = "items: ${dataset.get(position).des}"

            holder.itemPrice.text = "price = ${dataset.get(position).price} EGP"

            holder.itemDetail.movementMethod = ScrollingMovementMethod()


            holder.addToCart.setOnClickListener {
                if (position != RecyclerView.NO_POSITION) {


                    findNavController(manger).navigate(
                        CategoriesFragmentDirections.showDialoge(
                            id = dataset.get(position).id!!.toInt(),
                            name = dataset.get(position).name!!,
                            price = dataset.get(position).price!!
                        )
                    )
                }
            }


            val imgUri = dataset.get(position).img?.toUri()?.buildUpon()?.scheme("https")?.build()

            holder.itemImage.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }


        }


    }

    override fun getItemCount(): Int = (dataset!!.size)

}