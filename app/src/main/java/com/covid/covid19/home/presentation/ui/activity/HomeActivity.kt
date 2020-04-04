package com.covid.covid19.home.presentation.ui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Process
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat
import androidx.core.view.GravityCompat
import com.covid.covid19.R
import com.covid.covid19.common.BaseActivity
import com.covid.covid19.home.presentation.ui.fragments.CovidRiskFragment
import com.covid.covid19.home.presentation.ui.fragments.HomeFragment
import com.covid.covid19.home.presentation.ui.fragments.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.content_main.*


class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navigation.setOnNavigationItemSelectedListener(this)

        if (savedInstanceState == null){
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.simpleName)
                .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.menu_item_share -> {
                shareApplication()
                return true
            }
            R.id.menu_update -> {
                Toast.makeText(this, "Its Updated!", Toast.LENGTH_SHORT).show()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val fragment = HomeFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
            }
            R.id.nav_globe -> {
                val fragment = CovidRiskFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
            }
            R.id.nav_news -> {
                val fragment = NewsFragment()
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.simpleName)
                    .commit()
            }


        }
        return true
    }
    override fun onBackPressed() { //super.onBackPressed();
        isFinish("Are you sure you want to exit?")
    }

    private fun isFinish(message: String?) {
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> Process.killProcess(Process.myPid())
                    DialogInterface.BUTTON_NEGATIVE -> {
                       // dialog.cancel()
                    }
                }
            }
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Covid-19 Tracker")
        builder.setMessage(message)
            .setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }



    private fun shareApplication(){
      /*  val imageUri: Uri = Uri.parse(
            "android.resource://" + packageName
                    + "/drawable/" + "covidshare"
        )*/
        ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setChooserTitle("Covid tracker")
            .setText("No more going to websites for Covid-19 data, Just install this app once and there you are with all data updates!"+
                    "\n" +
                    "\n" +
                    "https://1drv.ms/u/s!AmQtH2XTelior3LxeY-EliQTwZqw?e=JBnU35")
            .startChooser()
    }

}
