package com.nurjahonbek.myschooladminpanel.ActivityTech

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.nurjahonbek.myschooladminpanel.ActivityNews.DataClass
import com.nurjahonbek.myschooladminpanel.ActivityNews.NewsAdapter
import com.nurjahonbek.myschooladminpanel.R
import com.nurjahonbek.myschooladminpanel.databinding.ActivityTeacherUpBinding

class TeacherUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherUpBinding

    lateinit var mList: ArrayList<DataClass>
    private lateinit var adapter: NewsAdapter
    private var category: String = ""
    private val storageReference: StorageReference =
        FirebaseStorage.getInstance().getReference("TechImage")
    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("TechData")
    private var uri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityTeacherUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
binding.imageView3.setImageResource(R.drawable.ic_launcher_background)
UploadData()
        binding.button.setOnClickListener {
            UploadData()
            val i = Intent(this, TeacherAcc::class.java)
            startActivity(i)
        }

    }

    @SuppressLint("MissingInflatedId", "InflateParams")
    private fun UploadData() {
        uploadImageToFirebase()

        binding.imageView3.setOnClickListener {
            resultLauncher.launch("image/*")


        }
    }


    private fun uploadImageToFirebase() {
        val t = binding.C.text.toString()
        val name=binding.age.text.toString()
        val science=binding.Science.text.toString()
        val number=binding.number.text.toString()
        val timestamp = System.currentTimeMillis()
        val id = "$timestamp"
        val imageRef = storageReference.child("${System.currentTimeMillis()}.jpg")
        if (t.isNotEmpty()&&name.isNotEmpty()&&science.isNotEmpty()&&number.isNotEmpty()) {

            uri?.let {
                imageRef.putFile(it)
                    .addOnSuccessListener { taskSnapshot ->
                        imageRef.downloadUrl.addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()

                            saveDataToDatabase(imageUrl, t,name,science,number)

                            Toast.makeText(this, "Upload Secces", Toast.LENGTH_SHORT).show()

                        }
                    }
                    .addOnFailureListener { e ->
                        // Rasmni yuklashda xatolik yuz berdi
                    }
            }}else {
            Toast.makeText(this, "is Empty", Toast.LENGTH_SHORT).show()

        }
    }




    private fun saveDataToDatabase(
        imageUrl: String,
        techName: String,
        techAge:String,
        techScience:String,
        tecNumber:String
    ) {



        val key = databaseReference.push().key

        if (key != null) {
            val data =
                TechData(imageUrl, techName,techAge,techScience,tecNumber)
            databaseReference.child(key).setValue(data)
        }
    }


    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) {

        uri = it
        binding.imageView3.setImageURI(it)
    }



}


