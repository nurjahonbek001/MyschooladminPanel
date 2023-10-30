package com.nurjahonbek.myschooladminpanel.ActivityTech

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
import com.nurjahonbek.myschooladminpanel.databinding.ActivityTeacherAccBinding

class TeacherAcc : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherAccBinding
    lateinit var mList: ArrayList<TechData>
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("TechData")
    private lateinit var adapter: TechAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTeacherAccBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getDataRew()
        getInit()
        fetchDataFromDatabase()
        binding.adNews.setOnClickListener {
            val i = Intent(this, TeacherUpActivity::class.java)
            startActivity(i)

        }
    }



    private fun getInit() {
        //firebase = FirebaseAuth.getInstance()
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && binding.adNews.isShown) {
                    binding.adNews.hide()
                } else if (dy < 0 && !binding.adNews.isShown) {
                    binding.adNews.show()
                }
            }
        })

    }

    private fun getDataRew() {
        mList = ArrayList()
//        val firebaseData = FirebaseDatabase.getInstance().getReference("Category")
//        firebaseData.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                mList.clear()
//                for (i in snapshot.children) {
//                    val data = i.getValue(CountryData::class.java)
//                    mList.add(data!!)
//                }
        adapter = TechAdapter(mList,this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this@TeacherAcc)
        binding.recyclerView.adapter = adapter

//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })

    }
    private fun fetchDataFromDatabase() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                mList.clear()
                for (dataSnapshot in snapshot.children) {
                    val data = dataSnapshot.getValue(TechData::class.java)
                    if (data != null) {
                        mList.add(data)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Ma'lumotlarni o'qishda xatolik yuz berdi
            }
        })
    }


}