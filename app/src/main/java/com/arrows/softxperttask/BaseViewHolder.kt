package com.arrows.softxperttask

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    private var mCurrentPosition: Int = 0

    init {
        itemView.setOnClickListener(::onClick)
    }

    protected abstract fun clear()
    open fun onBind(position: Int) {
        mCurrentPosition = position
        clear()
    }

    open fun getCurrentPosition(): Int {
        return mCurrentPosition
    }

    open fun onClick(v: View?) {}
}