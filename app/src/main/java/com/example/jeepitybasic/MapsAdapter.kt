package com.example.jeepitybasic

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jeepitybasic.models.UserMap

private const val TAG ="MapsAdapter"
class MapsAdapter(val context: Context, val UserMap: List<UserMap>, val onClickListener: OnClickListener) :RecyclerView.Adapter<MapsAdapter.ViewHolder>() {

    interface OnClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = UserMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userMap = UserMap[position]
        holder.itemView.setOnClickListener{
            Log.i(TAG, "tapped on position $position")
            onClickListener.onItemClick(position)
        }
        val textViewTitle = holder.itemView.findViewById<TextView>(android.R.id.text1)
        textViewTitle.text = userMap.title
    }






    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
