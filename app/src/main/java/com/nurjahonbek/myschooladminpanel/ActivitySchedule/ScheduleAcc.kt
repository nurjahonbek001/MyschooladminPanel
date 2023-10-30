package com.nurjahonbek.myschooladminpanel.ActivitySchedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nurjahonbek.myschooladminpanel.R
import com.nurjahonbek.myschooladminpanel.databinding.ActivityScheduleAccBinding

class ScheduleAcc : AppCompatActivity() {

    private  val firebaseData = FirebaseDatabase.getInstance().getReference("Table")
    private val ref = FirebaseDatabase.getInstance().getReference("Class")
    private lateinit var binding: ActivityScheduleAccBinding
    private var classId = ""
    private var classTitle = ""
    private lateinit var mTableList: ArrayList<ScheduleData>

    private lateinit var myAdapter: ScheduleAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleAccBinding.inflate(layoutInflater)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)


        classTitle = intent.getStringExtra("classNumber")!!
        classId = intent.getStringExtra("classNumberId")!!
        val classHarf = intent.getStringExtra("harf")!!

        loadDataTable()
        binding.addSchedule.setOnClickListener {
            val i = Intent(this, ScheduleUp::class.java)
            i.putExtra("classNumberId", classId)
            i.putExtra("classHarf", classHarf)

            startActivity(i)

        }
        binding.rewClassTable.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && binding.addSchedule.isShown) {
                    binding.addSchedule.hide()
                } else if (dy < 0 && !binding.addSchedule.isShown) {
                    binding.addSchedule.show()
                }
            }
        })


    }

    private fun loadDataTable() {
        mTableList = ArrayList()
        firebaseData.orderByChild("classId").equalTo(classId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    mTableList.clear()

                    for (i in snapshot.children) {
                        val m = i.getValue(ScheduleData::class.java)

                        if (m != null) {
                            mTableList.add(m)
                        }


                    }
                    myAdapter = ScheduleAdapter(mTableList, this@ScheduleAcc)
                    binding.rewClassTable.adapter = myAdapter

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ScheduleAcc, "error", Toast.LENGTH_SHORT).show()
                }
            })


    }


}
