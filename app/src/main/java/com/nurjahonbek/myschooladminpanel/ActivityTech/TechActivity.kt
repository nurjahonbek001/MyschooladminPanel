package com.nurjahonbek.myschooladminpanel.ActivityTech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.RawContacts.Data
import com.nurjahonbek.myschooladminpanel.ActivityNews.DataClass
import com.nurjahonbek.myschooladminpanel.databinding.ActivityTechBinding
import com.squareup.picasso.Picasso

class TechActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTechBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTechBinding.inflate(layoutInflater)
        setContentView(binding.root)

      val name= intent.getStringExtra("name",)
     val age=  intent.getStringExtra("age")
      val science= intent.getStringExtra("science",)
     val phone=  intent.getStringExtra("phoneNumber",)
     val Image=  intent.getStringExtra("Image",)


binding.ism.text=name
binding.yoshi.text=age
binding.fani.text=science
binding.tel.text=phone

        Picasso.get().load(Image).into(binding.imageView4)
    }
}