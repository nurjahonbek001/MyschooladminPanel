package com.nurjahonbek.myschooladminpanel.ActivityNotification

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.nurjahonbek.myschooladminpanel.ActivityNews.DataClass
import com.nurjahonbek.myschooladminpanel.Bells
import com.nurjahonbek.myschooladminpanel.MainActivity
import com.nurjahonbek.myschooladminpanel.databinding.ActivityNotificationUpBinding

class NotificationUp : AppCompatActivity() {
    lateinit var mList: ArrayList<DataClass>
    private lateinit var adapter: NotifAdapter
    private var category: String = ""
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("NotifData")
    private val storageReference: StorageReference =
        FirebaseStorage.getInstance().getReference("NotifImage")
    private var uri: Uri? = null
    private lateinit var binding: ActivityNotificationUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button2.setOnClickListener {

            getNotificationData()
            val i=Intent(this,Bells::class.java)
            startActivity(i)
            finish()

        }

    }

    private fun getNotificationData() {
        val title = binding.C.text.toString()
        val description = binding.notifDes.text.toString()
        if (TextUtils.isEmpty(title)){
            binding.C.error = "Bildirishnoma Qo'shilmadi"
        }else if (TextUtils.isEmpty(description)){
            binding.notifDes.error = "Bildirishnoma tavsifi kiritilmadi"
        }else{
            getNotificationDataFireBase(title,description)
        }


    }

    private fun getNotificationDataFireBase(title: String, description: String) {

        val timestamp = System.currentTimeMillis()
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["id"] = "$timestamp"
        hashMap["title"] = title
        hashMap["description"] = description
        hashMap["timestamp"] = timestamp


        databaseReference.child("$timestamp").setValue(hashMap)
            .addOnSuccessListener {

                onBackPressed()

                Toast.makeText(this, "getNotificationData success", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {

                onBackPressed()
                Toast.makeText(this, "Error getNotificationData", Toast.LENGTH_SHORT).show()

            }

    }
}
