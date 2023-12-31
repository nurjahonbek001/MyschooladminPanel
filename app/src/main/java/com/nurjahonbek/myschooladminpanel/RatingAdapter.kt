package com.nurjahonbek.myschooladminpanel

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.nurjahonbek.myschooladminpanel.databinding.RatingItemBinding
import com.squareup.picasso.Picasso


class RatingAdapter(private val mList: List<RatingData>, val context: Context): RecyclerView.Adapter<RatingAdapter.RatingViewHolder>() {

    inner class RatingViewHolder(private var binding:RatingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val deleteStudent = binding.btDeleteStudent
        fun bing(data: RatingData) {

            Picasso.get().load(data.imageUrl).into(binding.itemRatingImg)

            binding.itemRatingTxt.text = data.studentName

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingViewHolder {
        val binding = RatingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        val currentItem = mList[position]
        val id = currentItem.id
        val timestamp = currentItem.timestamp
        val studentName = currentItem.studentName
        val studentSureName = currentItem.studentSuraName
        val studentAge = currentItem.studentAge
        val imageUrl = currentItem.imageUrl
        val studentPhoneNumber = currentItem.studentClassNumber
        val studentScience = currentItem.studentScience

        holder.bing(currentItem)

        holder.itemView.setOnClickListener {
            val i  = Intent(context, RatingAcc2::class.java)
            i.putExtra("id",id)
            i.putExtra("timestamp",id)
            i.putExtra("studentName",studentName)
            i.putExtra("studentSureName",studentSureName)
            i.putExtra("studentAge",studentAge)
            i.putExtra("imageUrl",imageUrl)
            i.putExtra("studentClassNumber",studentPhoneNumber)
            i.putExtra("studentScience",studentScience)
            context.startActivity(i)

        }


        holder.deleteStudent.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete")
                .setMessage("delete student ")
                .setPositiveButton("yes") { _, _ ->

                    newsDelete(id,imageUrl)
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
    private fun newsDelete(id: String, urlImage:String) {
        val ref = FirebaseStorage.getInstance().getReferenceFromUrl(urlImage)

        ref.delete()
            .addOnSuccessListener {

                val firebaseData = FirebaseDatabase.getInstance().getReference("RatingData")

                firebaseData.child(id)
                    .removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(context, "success delete", Toast.LENGTH_SHORT).show()

                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "delete error", Toast.LENGTH_SHORT).show()
                    }

            }
            .addOnFailureListener {
                Toast.makeText(context, "delete error", Toast.LENGTH_SHORT).show()

            }
    }


}