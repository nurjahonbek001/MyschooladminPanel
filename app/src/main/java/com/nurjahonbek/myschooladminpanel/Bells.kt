package com.nurjahonbek.myschooladminpanel

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
import com.nurjahonbek.myschooladminpanel.ActivityNotification.NotifAdapter
import com.nurjahonbek.myschooladminpanel.ActivityNotification.NotifData
import com.nurjahonbek.myschooladminpanel.databinding.ActivityBellsBinding
import com.squareup.picasso.Picasso

class Bells : AppCompatActivity() {
    private lateinit var binding: ActivityBellsBinding
    lateinit var mList: ArrayList<NotifData>
    lateinit var myAdapter: NotifAdapter
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("NotifData")
    private lateinit var adapter: NotifAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBellsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getNotiData()
    }

        private fun getNotiData() {
            mList = ArrayList()
            val firebaseData = FirebaseDatabase.getInstance().getReference("NotifData")
            firebaseData.addValueEventListener(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    mList.clear()
                    for (i in snapshot.children) {
                        val data = i.getValue(NotifData::class.java)
                        mList.add(data!!)
                    }


                    myAdapter = NotifAdapter(mList, this@Bells)
                    binding.recyclerView.setHasFixedSize(true)
                    binding.recyclerView.layoutManager =
                        LinearLayoutManager(this@Bells)
                    binding.recyclerView.adapter = myAdapter

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }


    }
