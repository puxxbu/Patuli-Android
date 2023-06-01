package com.puxxbu.PatuliApp.ui.fragments.lesson.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.puxxbu.PatuliApp.R
import com.puxxbu.PatuliApp.data.model.LessonItemModel
import com.puxxbu.PatuliApp.databinding.ItemLessonBinding
import com.puxxbu.PatuliApp.ui.fragments.lesson.LessonListActivity

class LessonAdapter (private val items: List<LessonItemModel>) : RecyclerView.Adapter<LessonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = ItemLessonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding : ItemLessonBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LessonItemModel) {
           binding.apply {
               Glide.with(itemView.context)
                   .load(item.image_url)
                   .placeholder(R.drawable.ic_launcher_background)
                   .fitCenter()
                   .into(ivLessonPhoto)
               tvTitle.text = item.title
               tvDescription.text = item.description
               tvVideoCount.text = item.video_count.toString()
           }

            binding.card.setOnClickListener{
                val intent: Intent = Intent(itemView.context, LessonListActivity::class.java)
                intent.putExtra("lesson_id", item.title)
                itemView.context.startActivity(intent)
            }
        }
    }
}