package com.covid.covid19.home.presentation.adapter

import android.animation.ValueAnimator
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.covid.covid19.R
import com.covid.covid19.common.NumberUtils
import com.covid.covid19.home.StatClickListener
import com.covid.covid19.home.data.remote.model.StatisticsModel
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.android.synthetic.main.item_country.view.*
import kotlinx.android.synthetic.main.item_overview.view.*
import javax.inject.Inject


class StatsRecyclerAdapter @Inject constructor(private val statClickListener: StatClickListener) :
    RecyclerView.Adapter<StatsRecyclerAdapter.CasesViewHolder>() {
    private var statList : ArrayList<StatisticsModel> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CasesViewHolder(
            inflater.inflate(R.layout.item_overview, parent, false)
        )
    }

    fun setStatList(statList : ArrayList<StatisticsModel>){
        this.statList = statList
        notifyDataSetChanged()
    }

    override fun getItemCount() = statList.size

    override fun onBindViewHolder(holder: CasesViewHolder, position: Int) =
        holder.bind(statList[position])

    private fun startNumberChangeAnimator(finalValue: Int?, view: TextView) {
        val initialValue = NumberUtils.extractDigit(view.text.toString())
        val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue ?: 0)
        valueAnimator.duration = TEXT_ANIMATION_DURATION
        valueAnimator.addUpdateListener { value ->
            view.text = NumberUtils.numberFormat(value.animatedValue.toString().toInt())
        }
        valueAnimator.start()
    }

    inner class CasesViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(stats: StatisticsModel) {
            with(itemView) {

                if (stats.id == 1){
                    main_layout.visibility = View.GONE
                    countryView.visibility = View.VISIBLE
                    txt_update.text = "Last Updated : "+ stats.lastTime
                }else{
                    main_layout.visibility = View.VISIBLE
                    countryView.visibility = View.GONE
                }


                txt_country_location.text = stats.countryName
                txt_country_data.text = stats.totalCases
                txt_data_death.text = stats.totalDeath
                txt_data_rcv.text = stats.totalRecovered

                val totalActive = stats.totalCases?.toInt()
                val totalDeath = stats.totalDeath?.toInt()
                val totalRecovered = stats.totalRecovered?.toInt()


                startNumberChangeAnimator(totalActive, txt_confirmed)
                startNumberChangeAnimator(totalDeath, txt_deaths)
                startNumberChangeAnimator(totalRecovered, txt_recovered)

                tvCountry.text = stats.countryName
                itemView.setOnClickListener {
                    statClickListener.statListener(stats.id)
                }
               /* pie_chart.setOnClickListener{
                    statClickListener.statListener(stats.id)
                }
                txt_country_location.setOnClickListener{
                    statClickListener.statListener(stats.id)
                }
                txt_recovered.setOnClickListener{
                    statClickListener.statListener(stats.id)
                }

                txt_data_death.setOnClickListener{
                    statClickListener.statListener(stats.id)
                }

                txt_data_rcv.setOnClickListener{
                    statClickListener.statListener(stats.id)
                }


                countryView.setOnClickListener{
                    statClickListener.statListener(stats.id)
                }*/




                val pieDataSet = PieDataSet(
                    listOf(
                        totalActive?.toFloat()?.let { PieEntry(it, context.getString(R.string.confirmed)) },
                        totalRecovered?.toFloat()?.let { PieEntry(it, context.getString(R.string.recovered)) },
                        totalRecovered?.toFloat()?.let { PieEntry(it, context.getString(R.string.deaths)) }
                    ), context.getString(R.string.covid19)
                )
                val total = NumberUtils.numberFormat(
                    (totalRecovered?.let { totalDeath?.let { it1 -> totalActive?.plus(it)?.plus(it1) } }!!)
                ).toString()

                txt_cases.text = total

                with(context) {
                    val colors = arrayListOf(
                        color(R.color.color_confirmed),
                        color(R.color.color_recovered),
                        color(R.color.color_death)
                    )

                    pieDataSet.colors = colors
                }

                val pieData = PieData(pieDataSet)
                pieData.setDrawValues(false)
                with(pie_chart) {
                    if (data == pieData) return
                    data = pieData
                    legend.isEnabled = false
                    description = null
                    holeRadius = PIE_RADIUS
                    setHoleColor(context.color(R.color.cinder_grey))
                    setDrawEntryLabels(false)
                    animateY(PIE_ANIMATION_DURATION, com.github.mikephil.charting.animation.Easing.EaseInOutQuart)
                    invalidate()
                    //layout_cases.visibility = View.VISIBLE
                }
            }
            }
        }


    fun Context.color(resource: Int): Int {
        return ContextCompat.getColor(this, resource)
    }

    companion object {
        private const val TEXT_ANIMATION_DURATION = 1000L
        private const val PIE_ANIMATION_DURATION = 1500
        private const val PIE_RADIUS = 65f
    }
}