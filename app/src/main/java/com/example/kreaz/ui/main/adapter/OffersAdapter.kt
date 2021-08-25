package com.example.kreaz.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kreaz.R
import com.example.kreaz.network.offer


class OffersAdapter(
    private val context: Context?, private val dataset: List<offer>?

) : RecyclerView.Adapter<OffersAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var offerimage: ImageView

        init {
            offerimage = itemView.findViewById(R.id.offer_image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersAdapter.ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.offers_card, parent, false)
        return ViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val imgUri =
            dataset?.get(position)?.offer_img?.toUri()?.buildUpon()?.scheme("https")?.build()

        holder.offerimage.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }


    }

    override fun getItemCount(): Int = (dataset!!.size)


}



