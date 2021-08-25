package com.example.kreaz.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kreaz.R
import com.example.kreaz.network.CategoriesResponseData
import com.example.kreaz.ui.main.CategoriesFragment
import com.example.kreaz.ui.main.Communicator


class CategoriesAdapter(
    private val context: Context?, private val dataset: List<CategoriesResponseData>?,
    private val recyclerView: RecyclerView,
    var manager: CategoriesFragment
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemRecyclerView: RecyclerView
        var card: CardView

        init {
            itemImage = itemView.findViewById(R.id.categoryImage)
            itemTitle = itemView.findViewById(R.id.categoryTitle)
            itemDetail = itemView.findViewById(R.id.categoryDetail)
            itemRecyclerView = itemView.findViewById(R.id.item_recycler_view)
            card = itemView.findViewById(R.id.category_card_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_card, parent, false)
        return ViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = dataset?.get(position)?.name ?: "loading!!"
        holder.itemDetail.text = "items: ${dataset?.get(position)?.totalItems ?: "loading!!"}"

        val imgUri = dataset?.get(position)?.icon?.toUri()?.buildUpon()?.scheme("https")?.build()




        holder.itemImage.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
        holder.itemRecyclerView.adapter =
            ItemsAdapter(context, dataset?.get(position)?.items, manager)

        val childLayoutManager = LinearLayoutManager(
            holder.itemRecyclerView.context,
            RecyclerView.HORIZONTAL,
            false
        )
        childLayoutManager.initialPrefetchItemCount = 4


        holder.itemRecyclerView.apply {
            layoutManager = childLayoutManager
            adapter = ItemsAdapter(context, dataset?.get(position)?.items, manager)

            setRecycledViewPool(viewPool)
        }

        /*var IsExpand = false
         holder.card.setOnClickListener(){
             if(IsExpand)
             { holder.itemRecyclerView.visibility = View.GONE
                 IsExpand = false
         }
             else {holder.itemRecyclerView.visibility = View.VISIBLE
                 IsExpand = true



             }            }*/


    }

    override fun getItemCount(): Int = (dataset!!.size)


    /* var mExpandedPosition = -1

     val isExpanded = position === mExpandedPosition

     holder.detailsList.visibility = if (isExpanded) View.VISIBLE else View.GONE
     holder.itemView.isActivated = isExpanded

     holder.itemView.setOnClickListener {
         mExpandedPosition = if (isExpanded) -1 else position
         TransitionManager.beginDelayedTransition(recyclerView)


         notifyDataSetChanged()
     }*/


}



