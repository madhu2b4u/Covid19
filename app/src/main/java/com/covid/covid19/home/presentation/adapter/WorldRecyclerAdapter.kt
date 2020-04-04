package com.covid.covid19.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covid.covid19.R
import com.covid.covid19.home.data.remote.model.worldstats.Table
import kotlinx.android.synthetic.main.item_data.view.*
import javax.inject.Inject


class WorldRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<WorldRecyclerAdapter.CasesViewHolder>() {

    private var statList : ArrayList<Table> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CasesViewHolder(
            inflater.inflate(R.layout.item_data, parent, false)
        )
    }


    fun updateList(list: ArrayList<Table>) {
        statList = list
        notifyDataSetChanged()
    }

    fun setWroldList(statList : ArrayList<Table>){
        this.statList = statList
        notifyDataSetChanged()
    }

    override fun getItemCount() = statList.size

    override fun onBindViewHolder(holder: CasesViewHolder, position: Int) = holder.bind(statList[position])

    inner class CasesViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(table: Table) {
            with(itemView) {
                val context = itemView.context

                val totalActive =table.activeCases
                val totalDeath = table.totalDeaths
                val totalRecovered = table.totalRecovered
                val totalCases = table.totalCases

                txt_data.text = context.getString(R.string.data_confirmed,totalCases.toString())
                txt_death.text = context.getString(R.string.data_death,totalDeath.toString())
                txt_rcv.text = context.getString(R.string.data_recovered,totalRecovered.toString())
                txt_active.text = context.getString(R.string.data_active,totalActive.toString())

                val todayConfirmed = if(table.newCases ?.equals("")==true) "0" else table.newCases
                val todayDeath =if(table.newDeaths ?.equals("")==true) "0" else table.newDeaths

                txt_confirmed.text = context.getString(R.string.data_confirmed,todayConfirmed.toString())
                txt_recovered.text = context.getString(R.string.data_death,todayDeath.toString())
                txt_recovered.setTextColor(context.getColor(R.color.color_death))

                txt_location.text = table.country


            }
        }
    }

}