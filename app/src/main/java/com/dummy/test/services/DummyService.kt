package com.dummy.test.services

import com.dummy.test.models.DummyProducts
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DummyService {
    @GET("products?skip=5&")
    fun products(@Query("limit") limit: Int): Call<DummyProducts>

    @GET("products?skip=5&")
    fun storeToPref1(@Query("limit") limit: Int)

    @GET("products?skip=5&")
    fun getData(@Query("limit") limit: Int): Call<String>?
}