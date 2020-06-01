package com.appdev.tzwallhavenapp.async

import android.os.AsyncTask
import com.appdev.tzwallhavenapp.adapter.Item
import com.appdev.tzwallhavenapp.adapter.DataModel
import com.appdev.tzwallhavenapp.api.MyResponse
import com.appdev.tzwallhavenapp.listener.Listener
import retrofit2.Call
import java.lang.Exception
import java.net.URL

class MyATask(call1: Call<DataModel>, private val listener: Listener) :
    AsyncTask<Void, Void, List<Item>>() {

    private val call: Call<DataModel> = call1

    override fun doInBackground(vararg params: Void?): List<Item>? {

        try {
            val myJson = URL(call.request().url().toString()).readText()
            val items = MyResponse(myJson)

            return items.data
        }catch (e:Exception){

        }

        return null

    }

    override fun onPostExecute(result: List<Item>) {
        super.onPostExecute(result)
        listener.onLoadFinished(result)
    }
}