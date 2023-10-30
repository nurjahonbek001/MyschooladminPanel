package com.nurjahonbek.myschooladminpanel.ActivityNotification



import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.nurjahonbek.myschooladminpanel.Bells
import com.nurjahonbek.myschooladminpanel.MainActivity

import com.nurjahonbek.myschooladminpanel.databinding.NotifItemBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class NotifAdapter(private var mList: List<NotifData>, val c: Context) :
    RecyclerView.Adapter<NotifAdapter.NotificationViewHolder>() {

    inner class NotificationViewHolder(private var binding: NotifItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val btnDelete = binding.notiDelete
        fun bing(data: NotifData) {


            // binding.itmNewsDesc.setTrimExpandedText(" :more")
            binding.itemNotiTxt.text = data.title
            binding.itmNotiDesc.text = data.description


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding = NotifItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val currentItem = mList[position]
        val id = currentItem.id



        holder.bing(currentItem)


        val uzbekistanTimeZone = TimeZone.getTimeZone("Asia/Tashkent")


        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.timeZone = uzbekistanTimeZone






        holder.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(c)
            builder.setTitle("Delete")
                .setMessage("delete news ")
                .setPositiveButton("yes") { _, _ ->

                    newsDelete(id)
                }
                .setNegativeButton("no") { d, _ ->

                    d.dismiss()
                }
                .create()
                .show()

        }


    }
    override fun getItemCount(): Int {
        return mList.size
    }

    private fun newsDelete(id: String) {

        val firebaseData = FirebaseDatabase.getInstance().getReference("NotificationData")

        firebaseData.child(id)
            .removeValue()
            .addOnSuccessListener {
                Toast.makeText(c, "success delete", Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(c, "delete error", Toast.LENGTH_SHORT).show()
            }

    }

}