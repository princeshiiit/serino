package com.dummy.test.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.dummy.test.R
import com.dummy.test.models.Product
import com.bumptech.glide.Glide

class ProductListAdapter(
    private val context: Activity,
    private val list: List<Product>
) : ArrayAdapter<Product>(context, R.layout.custom_products_list, list) {

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = context.layoutInflater.inflate(R.layout.custom_products_list,
            null,true)

        val title = rootView.findViewById<TextView>(R.id.title)
        val price = rootView.findViewById<TextView>(R.id.price)
        val rating = rootView.findViewById<TextView>(R.id.description)

        val image = rootView.findViewById<ImageView>(R.id.r_img)

        val products = list[position]
        title.text = products.title
        price.text = "Price: ${products.price}"
        rating.text = "Description: \n${products.description}"
        Glide.with(context).load(products.thumbnail).into(image)

        return rootView


    }

}