package com.nurjahonbek.myschooladminpanel.ActivityNews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nurjahonbek.myschooladminpanel.R
import com.nurjahonbek.myschooladminpanel.databinding.ActivityNewsBinding
import com.squareup.picasso.Picasso

class   NewsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title=intent.getStringExtra("name")
        val description=intent.getStringExtra("Des")
        val image=intent.getStringExtra("image")

        binding.newsDesc.text=title
        binding.newsDesc.text=description
        Picasso.get().load(image).into(binding.newsImg)
    }
}