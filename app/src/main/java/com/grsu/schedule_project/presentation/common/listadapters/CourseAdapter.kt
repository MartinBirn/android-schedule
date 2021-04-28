package com.grsu.schedule_project.presentation.common.listadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grsu.schedule_project.databinding.ListItemSingleLineBinding
import com.grsu.schedule_project.presentation.common.OnClickListener

class CourseAdapter :
    ListAdapter<CourseItemViewModel, CourseAdapter.FacultyViewHolder>(CourseDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSingleLineBinding.inflate(layoutInflater, parent, false)
        return FacultyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacultyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FacultyViewHolder(private val binding: ListItemSingleLineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(courseItemViewModel: CourseItemViewModel) {
            binding.titleSingleLine.text = courseItemViewModel.courseTitle
            binding.root.setOnClickListener { courseItemViewModel.onCourseItemClickListener?.onClick() }
        }
    }
}

data class CourseItemViewModel(
    val courseId: String,
    val courseTitle: String,
    val onCourseItemClickListener: OnClickListener? = null
)

object CourseDiffCallback : DiffUtil.ItemCallback<CourseItemViewModel>() {

    override fun areItemsTheSame(
        oldItem: CourseItemViewModel,
        newItem: CourseItemViewModel
    ): Boolean {
        return oldItem.courseId == newItem.courseId
    }

    override fun areContentsTheSame(
        oldItem: CourseItemViewModel,
        newItem: CourseItemViewModel
    ): Boolean {
        return oldItem.courseId == newItem.courseId
    }
}