package com.appdev.tzwallhavenapp.listener

import com.appdev.tzwallhavenapp.adapter.Item

interface Listener {
    fun onLoadFinished(item: List<Item>)
}