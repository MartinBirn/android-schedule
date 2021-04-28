package com.grsu.schedule_project.presentation.common.listadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grsu.schedule_project.data.model.vo.FacultyVo
import com.grsu.schedule_project.databinding.ListItemSingleLineBinding
import com.grsu.schedule_project.presentation.common.OnClickListener

class FacultyAdapter :
    ListAdapter<FacultyItemViewModel, FacultyAdapter.FacultyViewHolder>(FacultyDiffCallback) {

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

        fun bind(facultyItemViewModel: FacultyItemViewModel) {
            binding.titleSingleLine.text = facultyItemViewModel.facultyVo.title
            binding.root.setOnClickListener { facultyItemViewModel.onFacultyItemClickListener?.onClick() }
        }
    }
}

data class FacultyItemViewModel(
    val facultyVo: FacultyVo,
    val onFacultyItemClickListener: OnClickListener? = null
)

object FacultyDiffCallback : DiffUtil.ItemCallback<FacultyItemViewModel>() {

    override fun areItemsTheSame(
        oldItem: FacultyItemViewModel,
        newItem: FacultyItemViewModel
    ): Boolean {
        return oldItem.facultyVo.localId == newItem.facultyVo.localId
    }

    override fun areContentsTheSame(
        oldItem: FacultyItemViewModel,
        newItem: FacultyItemViewModel
    ): Boolean {
        return oldItem.facultyVo == newItem.facultyVo
    }
}