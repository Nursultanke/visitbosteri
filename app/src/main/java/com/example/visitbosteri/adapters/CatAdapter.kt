package com.example.visitbosteri.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.visitbosteri.R
import com.example.visitbosteri.models.Categories
import kotlinx.android.synthetic.main.all_hotels_item.view.*
import kotlinx.android.synthetic.main.cat_item.view.*
import java.util.zip.Inflater

class CatAdapter(private val catList : List<Categories>, val catClickListener: OnCatClickListener) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {


    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val catImg: ImageView = itemView.catImg
        val catTitle: TextView = itemView.catTitle

        fun initialize(action: OnCatClickListener){
            itemView.setOnClickListener{
                action.onCatClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val itemView =  LayoutInflater.from(parent.context).inflate(R.layout.cat_item, parent, false)

        return CatViewHolder(itemView)
    }

    override fun getItemCount() = catList.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val currentItem = catList[position]

        holder.catImg.setImageResource(currentItem.CatImg)
        holder.catTitle.text = currentItem.CatTitle

        holder.initialize(catClickListener)
    }

    interface OnCatClickListener {

        fun onCatClick(position: Int)
    }
}