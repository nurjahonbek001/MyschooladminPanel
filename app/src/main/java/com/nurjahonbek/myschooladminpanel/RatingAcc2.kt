package com.nurjahonbek.myschooladminpanel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nurjahonbek.myschooladminpanel.databinding.ActivityRatingAcc2Binding
import com.nurjahonbek.myschooladminpanel.databinding.RatingItemBinding
import com.squareup.picasso.Picasso

class RatingAcc2 : AppCompatActivity() {
    private lateinit var binding: ActivityRatingAcc2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRatingAcc2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val name =intent.getStringExtra("studentName")
        val sureName =intent.getStringExtra("studentSureName")
        val age =intent.getStringExtra("studentAge")
        val clasNumber =intent.getStringExtra("studentClassNumber")
        val image =intent.getStringExtra("imageUrl")
        val science =intent.getStringExtra("studentScience")




        binding.ism.text=name
        binding.familya.text=sureName
        binding.yoshi.text=age
        binding.fani.text=science
        binding.sinfi.text=clasNumber
        Picasso.get().load(image).into(binding.imageView4)
//        i.putExtra("id",id)
//        i.putExtra("timestamp",id)
//        i.putExtra("studentName",studentName)
//        i.putExtra("studentSureName",studentSureName)
//        i.putExtra("studentAge",studentAge)
//        i.putExtra("imageUrl",imageUrl)
//        i.putExtra("studentClassNumber",studentPhoneNumber)
//        i.putExtra("studentScience",studentScience)
    }
}