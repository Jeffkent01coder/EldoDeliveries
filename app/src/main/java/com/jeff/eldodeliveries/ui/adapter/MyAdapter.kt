package com.jeff.eldodeliveries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jeff.eldodeliveries.R
import com.jeff.eldodeliveries.ui.Information

class MyAdapter(private val infoList : ArrayList<Information> ): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){

        val location : TextView = itemView.findViewById(R.id.tvLocation)
        val destination : TextView = itemView.findViewById(R.id.tvDestination)
        val item :TextView = itemView.findViewById(R.id.tvItem)
        val quantity : TextView = itemView.findViewById(R.id.tvQuantity)

    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_items,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder,position: Int) {

        val currentItem = infoList[position]

        holder.location.text = currentItem.location
        holder.destination.text = currentItem.destination
        holder.quantity.text = currentItem.quantity
        holder.item.text = currentItem.item

    }

    override fun getItemCount(): Int {
        return infoList.size
    }
}