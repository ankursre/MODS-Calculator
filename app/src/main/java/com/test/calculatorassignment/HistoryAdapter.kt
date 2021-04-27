package com.test.calculatorassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(var list: List<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_historyu, parent, false)
        return HistoryViewModel(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as HistoryViewModel).setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private inner class HistoryViewModel(view: View) :
        RecyclerView.ViewHolder(view) {
        var tvExpression: TextView = view.findViewById(R.id.tvExpression)
        fun setData(str: String?) {
            tvExpression.text = str
        }

    }

}