package com.nurjahonbek.myschooladminpanel.ActivityTech

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nurjahonbek.myschooladminpanel.ActivityPdf.ReadBook
import com.nurjahonbek.myschooladminpanel.MainActivity
import com.nurjahonbek.myschooladminpanel.databinding.TechItemBinding
import com.squareup.picasso.Picasso


class TechAdapter(private var mList: List<TechData>, val c: Context) :
    RecyclerView.Adapter<TechAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private var binding: TechItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bing(data: TechData) {

            Picasso.get().load(data.imageUrl).into(binding.imageView2)

            binding.textView3.text = data.name
            binding.textView5.text = data.age
            binding.textView6.text = data.science
            binding.textView7.text = data.phoneNumber


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = TechItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = mList[position]
        val name = currentItem.name
        val age = currentItem.age
        val science = currentItem.science
        val phoneNumber = currentItem.phoneNumber
        val image = currentItem.imageUrl
        holder.bing(currentItem)
        holder.itemView.setOnClickListener {
            val i = Intent(c, TeacherAcc::class.java)
            i.putExtra("name", name)
            i.putExtra("Image", image)
            i.putExtra("age", age)
            i.putExtra("science", science)
            i.putExtra("phoneNumber", phoneNumber)
            c.startActivity(i)

        }
        holder.itemView.setOnClickListener {
            val i = Intent(c, TechActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("Image", image)
            i.putExtra("age", age)
            i.putExtra("science", science)
            i.putExtra("phoneNumber", phoneNumber)
            c.startActivity(i)

        }





//        with(holder.binding) {
//            with(mList[position]) {
//                Picasso.get().load(this).into(imageView)
//            }
//        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }
}


