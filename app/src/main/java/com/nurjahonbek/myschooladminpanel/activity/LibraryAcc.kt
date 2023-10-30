package com.nurjahonbek.myschooladminpanel.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.nurjahonbek.myschooladminpanel.databinding.ActivityLibraryBinding

class LibraryAcc : AppCompatActivity() {
    private val firebaseData = FirebaseDatabase.getInstance().getReference("BooksData")
    private lateinit var myAdapter: LibraryAdapter
    private lateinit var mList: ArrayList<LibraryData>
    private lateinit var binding: ActivityLibraryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addLibrary.setOnClickListener {
            startActivity(Intent(this, LibraryUp::class.java))
        }

        getRewDataLibrary()

    }


    private fun getRewDataLibrary() {
        mList = ArrayList()
        binding.prgLibrary.visibility = View.VISIBLE
        firebaseData.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                mList.clear()
                for (i in snapshot.children) {
                    val data = i.getValue(LibraryData::class.java)
                    mList.add(data!!)
                }
                binding.prgLibrary.visibility = View.GONE
                myAdapter = LibraryAdapter(mList, this@LibraryAcc)
                binding.rewClassLibrary.setHasFixedSize(true)
                binding.rewClassLibrary.layoutManager = LinearLayoutManager(this@LibraryAcc)
                binding.rewClassLibrary.adapter = myAdapter

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}