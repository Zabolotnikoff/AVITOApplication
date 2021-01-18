package com.example.avitoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cell.view.*

class CellListAdapter() : RecyclerView.Adapter<CellListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return State.cellList!!.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = State.cellList?.get(position)
        item?.let { holder.bind(it) }
        holder.itemView.cellDelete.setOnClickListener {
            removeData(position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cellNumber: TextView = itemView.findViewById(R.id.cellNumber)
        fun bind(item: Int) {
            cellNumber.text = item.toString()
        }
    }

    fun addData() {
        val number = getNumber()
        val position = getPosition()
        State.cellList?.add(position, number)
        notifyItemInserted(position)
    }

    private fun getNumber(): Int {
        var result: Int

        if (State.deletedCellList?.size!! > 0) {
            result =State.deletedCellList[State.deletedCellList.size - 1]
            State.deletedCellList.removeLast()
        } else {
            result = State.cellList!!.size + 1
        }
        return result
    }

    private fun getPosition() = (Math.random() * State.cellList!!.size).toInt()

    private fun removeData(position: Int) {
        State.cellList?.let { State.deletedCellList?.add(it?.get(position)) }
        State.cellList?.removeAt(position)
        notifyDataSetChanged()

    }
}