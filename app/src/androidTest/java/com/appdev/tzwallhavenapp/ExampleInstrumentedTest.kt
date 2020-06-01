package com.appdev.tzwallhavenapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.appdev.tzwallhavenapp.adapter.DataModel
import com.appdev.tzwallhavenapp.adapter.Item
import com.appdev.tzwallhavenapp.async.MyATask
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import java.io.*
import kotlin.text.Charsets.UTF_8

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.appdev.tzwallhavenapp", appContext.packageName)
    }



    @Test
    @Throws(Exception::class)
    fun getName_isSasha() {

    }



}
