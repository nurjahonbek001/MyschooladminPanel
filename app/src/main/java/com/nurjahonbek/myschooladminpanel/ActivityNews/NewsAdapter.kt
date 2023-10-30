package com.nurjahonbek.myschooladminpanel.ActivityNews

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nurjahonbek.myschooladminpanel.MainActivity

import com.nurjahonbek.myschooladminpanel.databinding.NewsItemBinding
import com.squareup.picasso.Picasso

class NewsAdapter(private var mList: List<DataClass>, val c: Context) :
    RecyclerView.Adapter<NewsAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(private var binding: NewsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bing(data: DataClass) {

            Picasso.get().load(data.imageUrl).into(binding.imageView2)

            binding.textView3.text =data.title
            binding.textView9.text =data.description


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val currentItem = mList[position]
        val name = currentItem.title
        val Des = currentItem.description
        val image=currentItem.imageUrl
        holder.bing(currentItem)


        holder.itemView.setOnClickListener {
            val i  = Intent(c, NewsActivity::class.java)
            i.putExtra("name",name)
            i.putExtra("Des",Des)
            i.putExtra("image",image)
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