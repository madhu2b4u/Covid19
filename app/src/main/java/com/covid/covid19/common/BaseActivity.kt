package com.covid.covid19.common

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import dagger.android.support.DaggerAppCompatActivity


open class BaseActivity : DaggerAppCompatActivity() {

    private var progressBar: ProgressBar? = null

    val sharedPrefFile = "googleSharedPref"
    var sharedPreferences : SharedPreferences? =null
    var isLogedIn = false

    fun setSharedPref(){
        sharedPreferences  = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)
        isLogedIn = sharedPreferences?.getBoolean("sign_in",false)!!
    }

    fun setProgressBar(resId: Int) {
        progressBar = findViewById(resId)
    }
    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar?.visibility = View.INVISIBLE
    }

    fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    public override fun onStop() {
        super.onStop()
        hideProgressBar()
    }
}
