package com.appdev.tzwallhavenapp.adapter

import org.json.JSONObject

class Item(json: String) : JSONObject(json) {
        val imageUrl: String? = this.optString("thumbs")
        val originalImageUrl: String? = this.optString("path")
        val small: String? = this.optString("large")
    }