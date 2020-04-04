package com.covid.covid19.home.presentation.ui.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.covid.covid19.R
import com.covid.covid19.common.BaseFragment
import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.home.data.remote.model.indiastats.Statewise
import com.covid.covid19.home.presentation.adapter.IndiaRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_data.*


class IndiaFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            val indiaStatsResponse: IndiaStatsResponse? = arguments?.getParcelable("india")
            val list:ArrayList<Statewise> = indiaStatsResponse?.statewise as ArrayList<Statewise>
            val indiaRecyclerAdapter = IndiaRecyclerAdapter()
            list.removeAt(0)
            indiaRecyclerAdapter.setStatList(list)
            recycler_view.adapter =indiaRecyclerAdapter

            txt_search.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    //empty
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                    //empty
                }

                override fun afterTextChanged(editable: Editable) {
                    val filterState: ArrayList<Statewise> = ArrayList()
                    for (tempList in list) {
                        if (tempList.state?.toLowerCase()?.contains(editable.toString().toLowerCase())!!) {
                            filterState.add(tempList)
                        }
                    }
                    indiaRecyclerAdapter.updateList(filterState)

                }
            })

        }
    }




}
