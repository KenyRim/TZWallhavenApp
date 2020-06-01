package com.appdev.tzwallhavenapp.listener

import android.graphics.drawable.Drawable
import com.appdev.tzwallhavenapp.adapter.Item

class ClickListener {
    interface Click {
        fun onItemClicked(item: Item, position: Int, largeImage: String?,imageDrawable: Drawable)
    }
}