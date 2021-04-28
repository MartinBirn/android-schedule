package com.grsu.schedule_project.presentation.common.listadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grsu.schedule_project.data.model.vo.DepartmentVo
import com.grsu.schedule_project.databinding.ListItemSingleLineBinding
import com.grsu.schedule_project.presentation.common.OnClickListener

class DepartmentAdapter :
    ListAdapter<DepartmentItemViewModel, DepartmentAdapter.DepartmentViewHolder>(
        DepartmentDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepartmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSingleLineBinding.inflate(layoutInflater, parent, false)
        return DepartmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DepartmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DepartmentViewHolder(private val binding: ListItemSingleLineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(departmentItemViewModel: DepartmentItemViewModel) {
            binding.titleSingleLine.text = departmentItemViewModel.departmentVo.title
            binding.root.setOnClickListener { departmentItemViewModel.onDepartmentItemClickListener?.onClick() }
        }
    }
}

data class DepartmentItemViewModel(
    val departmentVo: DepartmentVo,
    val onDepartmentItemClickListener: OnClickListener? = null
)

object DepartmentDiffCallback : DiffUtil.ItemCallback<DepartmentItemViewModel>() {

    override fun areItemsTheSame(
        oldItem: DepartmentItemViewModel,
        newItem: DepartmentItemViewModel
    ): Boolean {
        return oldItem.departmentVo.localId == newItem.departmentVo.localId
    }

    override fun areContentsTheSame(
        oldItem: DepartmentItemViewModel,
        newItem: DepartmentItemViewModel
    ): Boolean {
        return oldItem.departmentVo == newItem.departmentVo
    }
}