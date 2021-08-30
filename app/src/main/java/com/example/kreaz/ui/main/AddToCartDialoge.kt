package com.example.kreaz.ui.main

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.kreaz.R
import com.example.kreaz.ui.main.models.CategoriesModel

class AddToCartDialoge : DialogFragment() {

    private lateinit var dialogView: View
    var quantity = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return dialogView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val seek = view.findViewById<SeekBar>(R.id.itemsCountBar)
        val count = view.findViewById<TextView>(R.id.itemCountShow)
        val inc = view.findViewById<Button>(R.id.add)
        val dec = view.findViewById<Button>(R.id.minus)

        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {

                count!!.text = progress.toString()
                quantity = seek.progress
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped

            }
        })
        inc?.setOnClickListener {
            var new = seek!!.progress + 1
            seek.progress = new

        }

        dec?.setOnClickListener {
            var new = seek!!.progress - 1
            seek.progress = new

        }
    }


    private val args: AddToCartDialogeArgs by navArgs()


    private val dialogeModel: CategoriesModel by activityViewModels {
        CategoriesModel.ItemsViewModelFactory(
            (activity?.application as com.example.kreaz.kreazApplication).database
                .cartDao()
        )
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var orderId = args.id
        var orderPrice = args.price
        var orderName = args.name
        dialogView = LayoutInflater.from(context).inflate(R.layout.add_to_cart_dialoge, null)


        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(dialogView)





            builder.setTitle("how many you want?")

                .setPositiveButton("Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        if (quantity == 0) {
                            dialog.dismiss()
                        } else {
                            dialogeModel.addNewItem(orderId, orderName, orderPrice, quantity)
                            dialog.dismiss()
                        }

                    })

                .setNegativeButton("cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })

            builder.create()


        } ?: throw IllegalStateException("Activity cannot be null")


    }
}





