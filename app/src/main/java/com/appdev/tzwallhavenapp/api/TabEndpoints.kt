package com.appdev.tzwallhavenapp.api

import com.appdev.tzwallhavenapp.adapter.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TabEndpoints {

    @GET("/api/v1/search")
    fun getImages(@Query("q") key: String): Call<DataModel>

}