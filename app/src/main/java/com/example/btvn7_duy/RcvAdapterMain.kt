package com.example.btvn7_duy

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.TOUCH_SLOP_DEFAULT
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.w3c.dom.Text

class RcvAdapterMain( var context: Context,var list: ArrayList<folder>): RecyclerView.Adapter<RcvAdapterMain.itemHolder>() {
     class itemHolder (item: View) : RecyclerView.ViewHolder(item){
        var title   : TextView    = item. findViewById(R.id.txtTitleLayout)
        var content : TextView    = item. findViewById(R.id.txtContentLayout)

    }

    //1. lấy objct khi click vào item
      var onItemClick : ((folder, Int) -> Unit)?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return itemHolder(view)
    }

    override fun onBindViewHolder(holder: itemHolder, position: Int) {

        var person = list.get(position)
        holder.title.text = person.title
        holder.content.text = person.content
        //2. click item
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(list.get(position),position)
        }
    }
    override fun getItemCount(): Int {
       return  list.size
    }
}