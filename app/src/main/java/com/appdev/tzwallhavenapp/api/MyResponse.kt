package com.appdev.tzwallhavenapp.api

import com.appdev.tzwallhavenapp.adapter.Item
import org.json.JSONObject

class MyResponse(json: String) : JSONObject(json) {
    val data = this.optJSONArray("data")
        ?.let {
            0.until(it.length()).map { i -> it.optJSONObject(i) }
        }
        ?.map { Item(it.toString()) }
}