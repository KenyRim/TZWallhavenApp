package com.appdev.tzwallhavenapp.fragment

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.appdev.tzwallhavenapp.R
import com.appdev.tzwallhavenapp.adapter.DataModel
import com.appdev.tzwallhavenapp.adapter.Item
import com.appdev.tzwallhavenapp.adapter.RvAdapter
import com.appdev.tzwallhavenapp.api.ServiceBuilder
import com.appdev.tzwallhavenapp.api.TabEndpoints
import com.appdev.tzwallhavenapp.async.MyATask
import com.appdev.tzwallhavenapp.db.DBOpenHelper
import com.appdev.tzwallhavenapp.listener.ClickListener
import com.appdev.tzwallhavenapp.listener.Listener
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class FragmentList : Fragment() , Listener, ClickListener.Click{

    private val request = ServiceBuilder.buildService(TabEndpoints::class.java)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            view.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subscriber.onNext(query!!)
                    progress_bar.visibility = View.VISIBLE
                    return false
                }
            })
        })
            .subscribe { text ->
                searchRequest(text,view)
            }

        searchRequest("top",view)

        return view
    }

    private fun searchRequest(query: String, view: View){
        val snackBar:Snackbar = Snackbar.make(view.root, "No Internet Connection!", Snackbar.LENGTH_SHORT)
        val call = request.getImages(query)
        call.enqueue(object : Callback<DataModel> {
            override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
                progress_bar.visibility = View.GONE

                snackBar.show()

                val db = DBOpenHelper(context, null)
                val items: List<Item> = db.selectAll()
                fillRecyclerView(items)

            }

            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {

                    MyATask(
                        call,
                        this@FragmentList
                    ).execute()
                }

            }
        })
    }

    override fun onLoadFinished(item: List<Item>) {
        if (progress_bar != null)
        progress_bar.visibility = View.GONE
        fillRecyclerView(item)

        val db = DBOpenHelper(context, null)
        db.deleteAll(context)

        for (i in item.indices) {
            val dBase = DBOpenHelper(context, null)

            dBase.addItem(item[i])

        }
    }

    fun fillRecyclerView(item: List<Item>){
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = RvAdapter(item,this@FragmentList)
        }
    }

    override fun onItemClicked(item: Item, position: Int, largeImage: String?, imageDrawable: Drawable) {
        showFrag(FragmentOriginal(),largeImage,imageDrawable)
    }


    private fun showFrag(fragment: Fragment, imageLarge: String?,imageDrawable: Drawable){
        val args = Bundle()

        val bitmap = (imageDrawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val bitmapData: ByteArray = stream.toByteArray()

        args.putString("ORIGIN_IMAGE", imageLarge)
        args.putByteArray("DRAWABLE_IMAGE",bitmapData)

        fragment.arguments = args

        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.container, fragment, fragment.javaClass.simpleName)
            ?.addToBackStack( "LIST" )
            ?.commit()
    }

}
