package com.example.visitbosteri.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.visitbosteri.R
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_detail_info.*


class DetailInfoActivity : AppCompatActivity() {

    val images = arrayOf(R.drawable.visitbosteri, R.drawable.visitbosteri, R.drawable.visitbosteri, R.drawable.visitbosteri)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_info)

        detailImg.pageCount = images.size
        detailImg.setImageListener(imageListener)

    }

    var imageListener =
        ImageListener { position, imageView -> imageView.setImageResource(images[position]) }
}