package com.dummy.test

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ProductDetailActivity : AppCompatActivity() {
    lateinit var txtTitleDetail : TextView
    lateinit var txtDescriptionDetail : TextView
    lateinit var txtPriceDetail : TextView
    lateinit var txtDiscountPDetail : TextView
    lateinit var txtRatingDetail : TextView
    lateinit var txtStockDetail : TextView
    lateinit var txtBrandDetail : TextView
    lateinit var txtCategoryDetail : TextView
    lateinit var imgDetail : ImageView
    lateinit var backBtn : Button
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        Log.d("item", ProductListActivity.selectedProduct!!.title.toString())
        txtTitleDetail = findViewById(R.id.txtTitleDetail)
        txtDescriptionDetail = findViewById(R.id.txtDescriptionDetail)
        txtPriceDetail = findViewById(R.id.txtPriceDetail)
        txtDiscountPDetail = findViewById(R.id.txtDiscountPDetail)
        txtRatingDetail = findViewById(R.id.txtRatingDetail)
        txtStockDetail = findViewById(R.id.txtStockDetail)
        txtBrandDetail = findViewById(R.id.txtBrandDetail)
        txtCategoryDetail = findViewById(R.id.txtCategoryDetail)
        imgDetail = findViewById(R.id.imgDetail)
        backBtn = findViewById(R.id.backBtn)

        txtTitleDetail.text = ProductListActivity.selectedProduct!!.title
        txtDescriptionDetail.text = ProductListActivity.selectedProduct!!.description
        txtPriceDetail.text = "Price: " + ProductListActivity.selectedProduct!!.price.toString()
        txtDiscountPDetail.text = "Discount: %" + ProductListActivity.selectedProduct!!.discountPercentage.toString()
        txtRatingDetail.text = "Rating: 5/"+ ProductListActivity.selectedProduct!!.rating.toString()
        txtStockDetail.text = "Stock: " + ProductListActivity.selectedProduct!!.stock.toString()
        txtBrandDetail.text = "Brand: " + ProductListActivity.selectedProduct!!.brand
        txtCategoryDetail.text = "Category: " + ProductListActivity.selectedProduct!!.category
        Glide.with(this).load(ProductListActivity.selectedProduct!!.images.get(0)).into(imgDetail)
        backBtn.setOnClickListener {
            val intent = Intent(this@ProductDetailActivity, ProductListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}