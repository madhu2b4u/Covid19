package com.covid.covid19.home.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.covid.covid19.R
import com.covid.covid19.home.data.remote.model.indiastats.Statewise
import kotlinx.android.synthetic.main.item_data.view.*
import javax.inject.Inject


class IndiaRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<IndiaRecyclerAdapter.CasesViewHolder>() {

    private var statList : ArrayList<Statewise> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CasesViewHolder(
            inflater.inflate(R.layout.item_data, parent, false)
        )
    }


    fun updateList(list: ArrayList<Statewise>) {
        statList = list
        notifyDataSetChanged()
    }

    fun setStatList(statList : ArrayList<Statewise>){
        this.statList = statList
        notifyDataSetChanged()
    }

    override fun getItemCount() = statList.size

    override fun onBindViewHolder(holder: CasesViewHolder, position: Int) = holder.bind(statList[position])

    inner class CasesViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(stats: Statewise) {
            with(itemView) {


                val context = itemView.context

                val totalActive = stats.active?.toInt()
                val totalDeath = stats.deaths?.toInt()
                val totalRecovered = stats.recovered?.toInt()
                val totalConfirmed = stats.confirmed?.toInt()

                txt_data.text = context.getString(R.string.data_confirmed,totalConfirmed.toString())
                txt_death.text = context.getString(R.string.data_death,totalDeath.toString())
                txt_rcv.text = context.getString(R.string.data_recovered,totalRecovered.toString())
                txt_active.text = context.getString(R.string.data_active,totalActive.toString())

                val todayConfirmed = stats.delta?.confirmed
                val todayRecovered = stats.delta?.recovered
                val todayDeath = stats.delta?.deaths

                txt_confirmed.text = context.getString(R.string.data_confirmed,todayConfirmed.toString())
                txt_recovered.text = context.getString(R.string.data_death,todayDeath.toString())
                txt_recovered.setTextColor(context.getColor(R.color.color_death))

                txt_location.text = stats.state


            }
        }
    }

}