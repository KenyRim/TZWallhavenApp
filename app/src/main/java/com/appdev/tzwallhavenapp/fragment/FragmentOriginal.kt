package com.appdev.tzwallhavenapp.fragment

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.appdev.tzwallhavenapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_otiginal.*
import kotlinx.android.synthetic.main.fragment_otiginal.view.*

class FragmentOriginal : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_otiginal, container, false)

        val args = arguments

        view.dismiss_btn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this@FragmentOriginal)?.commit()
        }

        val imageUrl = args?.getString("ORIGIN_IMAGE").toString()
        val b: ByteArray? = args?.getByteArray("DRAWABLE_IMAGE")
        val image: Drawable =
            BitmapDrawable(resources, BitmapFactory.decodeByteArray(b, 0, b!!.size))

        Glide.with(context!!)
            .load(imageUrl)
            .thumbnail(1f)
            .into(object : CustomTarget<Drawable?>() {
                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    view.ivTouch.setImageDrawable(image)
                }
                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    try {
                        ivTouch.setImageDrawable(resource)
                    }catch (npe: NullPointerException ){
                        view.ivTouch.setImageDrawable(image)
                    }
                }
            })

        return view
    }

}
