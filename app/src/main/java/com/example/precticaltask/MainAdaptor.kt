package com.example.precticaltask

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

class MainAdaptor constructor(var mContext:Context,var list:ArrayList<ModelClass>) :
    RecyclerView.Adapter<MainAdaptor.MainViewHolder>() {

    var itemClick :((item:ModelClass,position:Int,isLeft:Boolean,isRight:Boolean,isTop:Boolean,isBottom:Boolean)->Unit)?=null

    class MainViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var view: View
        init {
            view = itemView.findViewById(R.id.itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder:MainViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val isLeft = holder.itemView.left>0
            val isRight = holder.itemView.right>0
            val isTop = holder.itemView.top>0
            val isBottom = holder.itemView.bottom>0
            this.itemClick?.invoke(list[position],position,isLeft,isRight,isTop,isBottom)
        }

        if(list[position].isColor || list[position].isCenter){
            holder.view.setBackgroundColor(ResourcesCompat.getColor(mContext.resources,R.color.bg_border,null))
        }else{
            holder.view.background = ResourcesCompat.getDrawable(mContext.resources,R.drawable.bg_border_10,null)
        }
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItem(list:ArrayList<ModelClass>){
        this.list =list;
        notifyDataSetChanged();
    }
}