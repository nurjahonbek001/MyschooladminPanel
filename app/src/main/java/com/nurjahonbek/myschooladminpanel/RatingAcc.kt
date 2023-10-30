package com.nurjahonbek.myschooladminpanel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nurjahonbek.myschooladminpanel.databinding.ActivityRatingAccBinding

class RatingAcc : AppCompatActivity() {
    private lateinit var binding: ActivityRatingAccBinding
    private lateinit var myAdapter: RatingAdapter
    private lateinit var context: Context
    private lateinit var mList: ArrayList<RatingData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingAccBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRewRatingData()

        binding.addRatingBtn.setOnClickListener {

            startActivity(Intent(this, RatingUp::class.java))
            finish()
        }

    }


        private fun getRewRatingData() {
            binding.prgRating.visibility = View.VISIBLE
            mList = ArrayList()
            val firebaseData = FirebaseDatabase.getInstance().getReference("RatingData")
            firebaseData.addValueEventListener(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    mList.clear()
                    for (i in snapshot.children) {
                        val data = i.getValue(RatingData::class.java)
                        mList.add(data!!)
                    }
                    binding.prgRating.visibility = View.GONE

                    myAdapter = RatingAdapter(mList,this@RatingAcc)
                    binding.rewRating.setHasFixedSize(true)
                    binding.rewRating.layoutManager = LinearLayoutManager(this@RatingAcc)
                    binding.rewRating.adapter = myAdapter

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }