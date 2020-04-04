package com.covid.covid19.home.presentation.ui.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.covid.covid19.R
import com.covid.covid19.common.*
import com.covid.covid19.home.data.remote.model.indiastats.IndiaStatsResponse
import com.covid.covid19.home.data.remote.model.news.Article
import com.covid.covid19.home.data.remote.model.worldstats.WorldStatsResponse
import com.covid.covid19.home.presentation.adapter.NewsRecyclerAdapter
import com.covid.covid19.home.presentation.adapter.StatsRecyclerAdapter
import com.covid.covid19.home.presentation.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.fragment_stat.*
import kotlinx.android.synthetic.main.fragment_stat.rlError
import kotlinx.android.synthetic.main.fragment_stat.swipeRefresh
import javax.inject.Inject


class NewsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mHomeViewModel: HomeViewModel
    private var newsRecyclerAdapter : NewsRecyclerAdapter? = null
    private var listOfNews :ArrayList<Article>? = ArrayList()
    private var page = 100




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            mHomeViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(HomeViewModel::class.java)
            setProgressBar(R.id.progressBar)
            mHomeViewModel.loadNews(page)
            initViews()
            mHomeViewModel.newsResult.observe(this@NewsFragment, Observer {
                when (it.status) {
                    Status.LOADING -> showLoading()
                    Status.ERROR -> {
                        hideLoading()
                    }
                    Status.SUCCESS -> {
                        hideLoading()
                        it.data?.let { news ->
                            listOfNews = news.articles as ArrayList<Article>?
                            rlError.visibility = View.GONE
                            swipeRefresh.visibility =View.VISIBLE
                            swipeRefresh.isRefreshing = false
                            listOfNews?.let { it1 -> newsRecyclerAdapter?.setStatList(it1) }
                        }
                    }
                }
            })

        }
    }

    private fun showLoading() {
        showProgressBar()
        rvNews.visibility = View.GONE
    }

    private fun initViews() {
        val mLayoutManager = LinearLayoutManager(context)
        rvNews.layoutManager = mLayoutManager
        newsRecyclerAdapter = NewsRecyclerAdapter()
        rvNews.adapter = newsRecyclerAdapter
        (rvNews.adapter as NewsRecyclerAdapter).notifyDataSetChanged()
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            mHomeViewModel.loadNews(page)
        }
/*
        rvNews.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                //check for scroll down
                {
                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (loading && context?.let { Utils().isNetworkAvailable(it) }!!) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            isFromPagination = true
                            mHomeViewModel.loadNews((page++))
                        }
                    }
                }
            }
        })*/



    }

    private fun hideLoading() {
        hideProgressBar()
        rvNews.visibility = View.VISIBLE
        if (swipeRefresh.isRefreshing)
            swipeRefresh.isRefreshing = false
    }




}
