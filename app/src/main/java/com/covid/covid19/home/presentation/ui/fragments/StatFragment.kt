package com.covid.covid19.home.presentation.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.covid.covid19.BuildConfig
import com.covid.covid19.R
import com.covid.covid19.common.*
import com.covid.covid19.home.StatClickListener
import com.covid.covid19.home.data.remote.model.StatisticsModel
import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.home.data.remote.model.worldstats.WorldStatsResponse
import com.covid.covid19.home.presentation.adapter.StatsRecyclerAdapter
import com.covid.covid19.home.presentation.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_stat.*
import javax.inject.Inject


class StatFragment : BaseFragment(),StatClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mHomeViewModel: HomeViewModel

    private var listOfStats : ArrayList<StatisticsModel> = ArrayList()

    private var statsRecyclerAdapter : StatsRecyclerAdapter? = null

    private var indiaStatsResponse : IndiaStatsResponse ? =null
    private var worldStatsResponse : WorldStatsResponse ? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenStarted {

        mHomeViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(HomeViewModel::class.java)

        setProgressBar(R.id.progressBar)
        initViews()
        setAds(activity!!)
        if (!Utils().isNetworkAvailable(context!!)){
            rlError.visibility = View.VISIBLE
        }else{
            rlError.visibility = View.GONE
        }

            mHomeViewModel.loadIndia(Urls.INDIA_URL,true)
            mHomeViewModel.loadWorld(Urls.WORLD_URL,true)
            mHomeViewModel.checkUpdate(Urls.DOWNLOAD_JSON,true)

            mHomeViewModel.indiaResult.observe(this@StatFragment, Observer {
                when (it.status) {
                    Status.LOADING -> showLoading()
                    Status.ERROR -> showLoading()
                    Status.SUCCESS -> {
                        showLoading()
                        it.data?.let { india ->
                            indiaStatsResponse = india
                            rlError.visibility = View.GONE
                            swipeRefresh.isRefreshing = false
                        }
                    }
                }
            })

            mHomeViewModel.updateResult.observe(this@StatFragment, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let { apkData ->
                            if (apkData.versionCode == BuildConfig.VERSION_CODE &&
                                apkData.versionName.equals(BuildConfig.VERSION_NAME)) {
                                tvUpdate.visibility = View.GONE
                            }else
                                tvUpdate.visibility = View.VISIBLE


                        }
                    }
                }
            })


            mHomeViewModel.worldResult.observe(this@StatFragment, Observer {
                when (it.status) {
                    Status.LOADING -> showLoading()
                    Status.ERROR -> hideLoading()
                    Status.SUCCESS -> {
                        showLoading()
                        it.data?.let { world ->
                            worldStatsResponse = world
                            swipeRefresh.isRefreshing = false
                            rlError.visibility = View.GONE
                            listOfStats.clear()
                            val india = indiaStatsResponse!!
                            val activeIndiaCases = india.statewise?.get(0)?.active
                            val deathIndiaCases = india.statewise?.get(0)?.deaths
                            val recoveredIndiaCases = india.statewise?.get(0)?.recovered
                            val activeIndiaCase = india.statewise?.get(0)?.confirmed
                            val lastTime = india.keyValues?.get(0)?.lastupdatedtime

                            val activeCases = world.reports?.get(0)?.cases.toString()
                            val deathCases = world.reports?.get(0)?.deaths.toString()
                            val recoveredCases = world.reports?.get(0)?.recovered.toString()
                            val activeCase = 0.toString()
                            listOfStats.add(StatisticsModel(deathCases,activeCase,recoveredCases,"World",activeCases,2,""))
                            listOfStats.add(StatisticsModel(deathIndiaCases,activeIndiaCases,recoveredIndiaCases,"India",activeIndiaCase,1,lastTime))
                            showInterstitial(activity!!)
                            statsRecyclerAdapter?.setStatList(listOfStats)
                            hideLoading()

                        }
                    }

                }
            })
        }
    }

    private fun showLoading() {
        showProgressBar()
        rvSummary.visibility = View.GONE
    }

    private fun initViews() {
        val mLayoutManager = LinearLayoutManager(context)
        rvSummary.layoutManager = mLayoutManager
        statsRecyclerAdapter = StatsRecyclerAdapter(this)
        rvSummary.adapter = statsRecyclerAdapter
        (rvSummary.adapter as StatsRecyclerAdapter).notifyDataSetChanged()
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            listOfStats.clear()
            mHomeViewModel.loadIndia(Urls.INDIA_URL,true)
            mHomeViewModel.loadWorld(Urls.WORLD_URL,true)
            mHomeViewModel.checkUpdate(Urls.DOWNLOAD_JSON,true)

        }
    }

    private fun prepareBundleForIndia(indiaStatsResponse: IndiaStatsResponse): Bundle {
        val bundle = Bundle()
        bundle.putParcelable("india", indiaStatsResponse)
        return bundle
    }

    private fun prepareBundleForWorld(worldStatsResponse: WorldStatsResponse): Bundle {
        val bundle = Bundle()
        bundle.putParcelable("world", worldStatsResponse)
        return bundle
    }
    private fun hideLoading() {
        hideProgressBar()
        rvSummary.visibility = View.VISIBLE
        if (swipeRefresh.isRefreshing)
            swipeRefresh.isRefreshing = false
    }

    override fun statListener(id: Int) {
        if (id == 1){
            view?.let {
                Navigation.findNavController(it).navigate(R.id.action_india,
                    indiaStatsResponse?.let { indiastat -> prepareBundleForIndia(indiastat) })
            }
        }else  if (id == 2){
            view?.let {
                Navigation.findNavController(it).navigate(R.id.action_world,
                    worldStatsResponse?.let { worldstat -> prepareBundleForWorld(worldstat) })
            }
        }
    }


}
