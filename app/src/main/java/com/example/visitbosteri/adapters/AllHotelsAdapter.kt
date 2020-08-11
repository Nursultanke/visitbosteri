package com.example.visitbosteri.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.visitbosteri.R
import com.example.visitbosteri.models.AllHotels
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.all_hotels_item.view.*

class AllHotelsAdapter(private val hotelList: List<AllHotels>, val onHotelClick: OnHotelClickListener):
    RecyclerView.Adapter<AllHotelsAdapter.AllHotelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHotelViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.all_hotels_item, parent, false)

        return AllHotelViewHolder(itemView)
    }

    override fun getItemCount() = hotelList.size

    override fun onBindViewHolder(holder: AllHotelViewHolder, position: Int) {
        val currentItem = hotelList[position]

        Picasso.get()
            .load(currentItem.photo_main)
            .resize(1000, 1000)
            .centerCrop()
            .into(holder.AllHotelPhoto)

       // holder.AllHotelPhoto.setImageResource(currentItem.photo_mainn)
        holder.AllHotelTitle.text = currentItem.title
        holder.AllHotelAddress.text = currentItem.address
        holder.AllHotelCategory.text = currentItem.category
        holder.AllHotelPrice.text = currentItem.price.toString()



        holder.initialize(onHotelClick)

    }

    class AllHotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val AllHotelPhoto: ImageView = itemView.AllHotelImg
        val AllHotelTitle: TextView = itemView.AllHotelTitle
        val AllHotelAddress: TextView = itemView.AllHotelAddress
        val AllHotelPrice: TextView = itemView.AllHotelPrice
        val AllHotelCategory: TextView = itemView.AllHotelCategory
        //val AllHotelDate: TextView = itemView.AllHotelDate

        fun initialize(action: OnHotelClickListener){
            itemView.setOnClickListener{
                action.onHotelClick(adapterPosition)
            }
        }
    }

    interface OnHotelClickListener {
        fun onHotelClick(position: Int)
    }
}