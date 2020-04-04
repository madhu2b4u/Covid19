package com.covid.covid19.recyclerview

interface NotifyableAdapter {
    val listener: OnNotifyDatasetChangeListener?
        get() = null

    fun setOnNotifyDataSetChangeListener(onNotifyDataSetChangeListener: OnNotifyDatasetChangeListener)
}
