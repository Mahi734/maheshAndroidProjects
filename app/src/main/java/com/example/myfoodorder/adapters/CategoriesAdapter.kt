package com.example.myfoodorder.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myfoodorder.data.Category
import com.example.myfoodorder.databinding.CategoryItemBinding

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private var categoryList = ArrayList<Category>()

   var onItemClick:((Category)-> Unit)?=null

    fun setCategoryList(categoriesList: List<Category>) {
        this.categoryList = categoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }
    override fun getItemCount(): Int {
        return categoryList.size
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.
        with(holder.itemView).
        load(categoryList[position].strCategoryThumb)
            .into(holder.binding.imgCategory)

        holder.binding.tvCategoryName.text = categoryList[position].strCategory

        holder.itemView.setOnClickListener{
            onItemClick!!.invoke(categoryList[position])
        }
    }
     class CategoryViewHolder(
        val binding: CategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

    }
}