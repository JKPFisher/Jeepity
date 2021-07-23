package com.example.jeepitybasic

import android.accounts.AccountManager.get
import android.content.ClipDescription
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jeepitybasic.models.UserMap
import kotlinx.android.synthetic.main.recyler_view_layout.view.*
import java.lang.reflect.Array.get
import java.util.Arrays.toString
import java.util.Objects.toString
import com.example.jeepitybasic.models.Place



private const val TAG ="MapsAdapter"
class MapsAdapter(val context: Context, val UserMap: List<UserMap>, private val onClickListener: OnClickListener ) :RecyclerView.Adapter<MapsAdapter.ViewHolder>() {
// val Place: List<Place>,
    interface OnClickListener{
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyler_view_layout, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount() = UserMap.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userMap = UserMap[position]

        holder.itemView.setOnClickListener{
            Log.i(TAG, "tapped on position $position")
            onClickListener.onItemClick(position)
        }
        val textViewTitle = holder.itemView.findViewById<TextView>(R.id.LineOne)

        textViewTitle.text = userMap.title
        //textViewDesc.text = userMap.places.description



    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.imageView
        val textView1: TextView = itemView.LineOne

    }
}
