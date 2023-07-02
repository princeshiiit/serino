package com.dummy.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.dummy.test.adapter.ProductListAdapter
import com.dummy.test.configs.ApiClient
import com.dummy.test.models.DummyProducts
import com.dummy.test.services.DummyService
import com.google.gson.Gson
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductListActivity : AppCompatActivity() {
    lateinit var dummyService2 : DummyService
    lateinit var productsListView : ListView

    companion object Product
    {
        var selectedProduct : com.dummy.test.models.Product? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        val sharedpref = getSharedPreferences("localpref", MODE_PRIVATE)
        val editor = sharedpref.edit()
        productsListView = findViewById(R.id.productsListView)

        if (sharedpref.getString("pref_data","def") == "def") {
            dummyService2 = ApiClient.getClient().create(DummyService::class.java)
            dummyService2.getData(10)?.enqueue(object: Callback<String> {

                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    Log.e("TEST1:",response.body().toString())
                    editor.putString("pref_data", response.body().toString()).apply()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    //TODO toast Error
                    Log.e("Error",t.toString())
                }

            })

            dummyService2.products(10).enqueue(object : Callback<DummyProducts> {
                override fun onResponse(call: Call<DummyProducts>, response:
                Response<DummyProducts>) {
                    val data = response.body()
                    val customAdapter = ProductListAdapter(this@ProductListActivity,
                            data!!.products)
                    productsListView.adapter = customAdapter
                }

                override fun onFailure(call: Call<DummyProducts>, t: Throwable) {
                    //TODO toast Error
                    Log.e("Error",t.toString())
                }

            })

        } else {
            val gson = Gson()
            val prefData = sharedpref.getString("pref_data","default")
            Log.e("prefData:",prefData.toString())
            val parser = JsonParser()
            val prefDataObj = parser.parse(prefData).asJsonObject
            val person = gson.fromJson(prefDataObj, DummyProducts::class.java)
            val customAdapter = ProductListAdapter(this@ProductListActivity,
                person!!.products)
            productsListView.adapter = customAdapter
        }

        productsListView.setOnItemClickListener { _, _, i, _ ->
            val selectedItem = productsListView.getItemAtPosition(i) as
                    com.dummy.test.models.Product
            selectedProduct = selectedItem
            val intent = Intent(this@ProductListActivity,
                ProductDetailActivity::class.java)
            startActivity(intent)
        }

    }
}