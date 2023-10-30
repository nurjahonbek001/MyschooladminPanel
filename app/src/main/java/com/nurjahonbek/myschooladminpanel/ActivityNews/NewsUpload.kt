package com.nurjahonbek.myschooladminpanel.ActivityNews

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
import com.nurjahonbek.myschooladminpanel.databinding.ActivityNewsUploadBinding

class NewsUpload : AppCompatActivity() {
    lateinit var mList: ArrayList<DataClass>
    private lateinit var adapter: NewsAdapter
    private var category: String = ""
    private val storageReference: StorageReference =
        FirebaseStorage.getInstance().getReference("newsImage")
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("newsData")
    private var uri: Uri? = null
    private lateinit var binding: ActivityNewsUploadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {

            getNewsData()
        }
        binding.imageView.setOnClickListener {
            resultLauncher.launch("image/*")
        }
    }

    private fun getNewsData() {

        val title = binding.C.text.toString()
        val description = binding.Des.text.toString()
        if (TextUtils.isEmpty(title)) {
            binding.C.error = "Yangiliklar Sarlavhasi kiriting"

        } else if ( TextUtils.isEmpty(description)) {

            binding.Des.error = "Yangiliklar tavsifi kiriting"
        } else if (uri == null) {
            Toast.makeText(this, "Rasm tanlanmagan", Toast.LENGTH_SHORT).show()
        } else {
            binding.C.error = null
            binding.Des.error = null
            getNewsDataFireBase(title,description)


        }
    }

    private fun getNewsDataFireBase(title: String, description: String) {



        val timestamp = System.currentTimeMillis()
        val id = "$timestamp"
        val imageRef = storageReference.child("${System.currentTimeMillis()}.jpg")

        uri?.let {
            imageRef.putFile(it)
                .addOnSuccessListener { taskSnapshot ->
                    imageRef.downloadUrl.addOnSuccessListener { uri ->
                        val imageUrl = uri.toString()

                        saveDataToDatabase(
                            id,
                            timestamp,
                            imageUrl,
                            title,
                            description
                        )


                        onBackPressed()
                        Toast.makeText(this, "News data save", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->


                    // Rasmni yuklashda xatolik yuz berdi
                    Toast.makeText(this, "News data error ${e.message}", Toast.LENGTH_SHORT)
                        .show()


                }


        }

    }

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {

        uri = it
        binding.imageView.setImageURI(it)

    }

    private fun saveDataToDatabase(
        id: String,
        timestamp: Long,
        imageUrl: String,
        title: String,
        description: String,

        ) {
        val hashMap: HashMap<String, Any> = HashMap()

        hashMap["id"] = id
        hashMap["title"] = title
        hashMap["imageUrl"] = imageUrl
        hashMap["description"] = description
        hashMap["timestamp"] = timestamp

        databaseReference.child(id)
            .setValue(hashMap)
            .addOnSuccessListener {
                Toast.makeText(this, "pdf data upload successful", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "pdf data upload error", Toast.LENGTH_SHORT).show()

            }


    }



}