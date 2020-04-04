package com.covid.covid19.home.presentation.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.covid.covid19.R
import com.covid.covid19.home.data.remote.model.news.Article
import kotlinx.android.synthetic.main.list_item_news.view.*
import javax.inject.Inject


class NewsRecyclerAdapter @Inject constructor() : RecyclerView.Adapter<NewsRecyclerAdapter.CasesViewHolder>() {

    private var statList: MutableList<Article> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CasesViewHolder(
            inflater.inflate(R.layout.list_item_news, parent, false)
        )
    }

    fun populateNews(users: List<Article>, isFromPaginaton: Boolean) {
        if (!isFromPaginaton) statList.clear()
        statList.addAll(users)
        notifyDataSetChanged()
    }

    fun setStatList(statList : ArrayList<Article>){
        this.statList = statList
        notifyDataSetChanged()
    }

    override fun getItemCount() = statList.size

    override fun onBindViewHolder(holder: CasesViewHolder, position: Int) = holder.bind(statList[position])

    inner class CasesViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(article: Article) {
            with(itemView) {

                val context = itemView.context
                tvTitle.text = article.title
                tvDescription.text =article.description
                Glide.with(context).load(article.urlToImage).error(R.drawable.noimage).into(ivImage)
                itemView.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(browserIntent)
                }



            }
        }
    }

}