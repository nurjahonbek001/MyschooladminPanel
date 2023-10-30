package com.nurjahonbek.myschooladminpanel.ActivityNotification

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nurjahonbek.myschooladminpanel.databinding.ActivityNotificationAccBinding

class NotificationAcc : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationAccBinding
    lateinit var mList: ArrayList<NotifData>
    private lateinit var myAdapter: NotifAdapter
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("NotificationData")
    private lateinit var adapter: NotifAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNotificationAccBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
binding.adNews.setOnClickListener {
    val i = Intent(this,NotificationUp::class.java)
    startActivity(i)

}
    }
}