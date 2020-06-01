package com.appdev.tzwallhavenapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.appdev.tzwallhavenapp.fragment.FragmentList


class MainActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FragmentList(),"LIST")
            .commit()
    }

    override fun onBackPressed() {
        val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment?.tag.equals("LIST")){
            finish()
        }else{
            super.onBackPressed()
        }
    }

}