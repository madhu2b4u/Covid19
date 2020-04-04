package com.covid.covid19.home.presentation.ui.fragments


import android.R.color
import android.content.res.Configuration
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.covid.covid19.R
import com.covid.covid19.common.BaseFragment
import com.covid.covid19.common.ViewModelFactory
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import javax.inject.Inject


class AboutFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val aboutPage = AboutPage(context)
            .isRTL(false)
            .setImage(R.drawable.img_covid_logo)
            .setDescription("ssss")
            .addItem(Element().setTitle("Version 1.0"))
            .addGroup("Connect with us")
            .addEmail("raydriiger@gmail.com")
            .addInstagram("wanderingmak")
            .addItem(getCopyRightsElement())
            .create()

        return aboutPage
    }

    fun getCopyRightsElement(): Element? {
        val copyRightsElement = Element()
        copyRightsElement.title = "copyrights"
        copyRightsElement.iconDrawable = R.drawable.ic_logo
        copyRightsElement.iconTint = R.color.about_item_icon_color
        copyRightsElement.iconNightTint = color.white
        copyRightsElement.gravity = Gravity.CENTER
        copyRightsElement.onClickListener =
            View.OnClickListener {
                Toast.makeText(activity, "copyrights", Toast.LENGTH_SHORT).show()
            }
        return copyRightsElement
    }

    fun simulateDayNight(currentSetting: Int) {
        val DAY = 0
        val NIGHT = 1
        val FOLLOW_SYSTEM = 3
        val currentNightMode = (resources.configuration.uiMode
                and Configuration.UI_MODE_NIGHT_MASK)
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO
            )
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_YES
            )
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            )
        }
    }




}
