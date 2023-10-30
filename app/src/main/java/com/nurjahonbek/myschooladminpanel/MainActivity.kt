package com.nurjahonbek.myschooladminpanel

import com.nurjahonbek.myschooladminpanel.activity.LibraryAcc
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nurjahonbek.myschooladminpanel.ActivityNews.NewsAcc
import com.nurjahonbek.myschooladminpanel.ActivityNotification.NotificationAcc
import com.nurjahonbek.myschooladminpanel.ActivitySchedule.ScheduleAcc
import com.nurjahonbek.myschooladminpanel.ActivityTech.TeacherAcc
import com.nurjahonbek.myschooladminpanel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.news.setOnClickListener {
            val i =Intent(this, NewsAcc::class.java)
            startActivity(i)
        }
        binding.teacher.setOnClickListener {
            val i =Intent(this, TeacherAcc::class.java)
            startActivity(i)
        }
        binding.classInfo.setOnClickListener {
            val i =Intent(this, ScheduleAcc::class.java)
            startActivity(i)
        }
        binding.library.setOnClickListener {
            val i =Intent(this, LibraryAcc::class.java)
            startActivity(i)
        }
        binding.notification.setOnClickListener {
            val i =Intent(this, NotificationAcc::class.java)
            startActivity(i)
        }
        binding.tournament.setOnClickListener {
            val i =Intent(this,RatingAcc::class.java)
            startActivity(i)
        }
        binding.bells.setOnClickListener {
            val i = Intent(this,Bells::class.java)
            startActivity(i)
        }
    }
}