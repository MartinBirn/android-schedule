package com.grsu.schedule_project.presentation.common.listadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grsu.schedule_project.databinding.ListItemSingleLineBinding
import com.grsu.schedule_project.presentation.common.OnClickListener

open class SingleLineAdapter<T : AbstractItemViewModel> :
    ListAdapter<T, SingleLineAdapter.SingleLineViewHolder>(SingleLineDiffCallback<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleLineViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemSingleLineBinding.inflate(layoutInflater, parent, false)
        return SingleLineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SingleLineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SingleLineViewHolder(private val binding: ListItemSingleLineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(abstractItemViewModel: AbstractItemViewModel) {
            binding.titleSingleLine.text = abstractItemViewModel.title
            binding.root.setOnClickListener { abstractItemViewModel.onClickListener?.onClick() }
        }
    }
}

abstract class AbstractItemViewModel(
    open val id: String,
    open val title: String?,
    open val onClickListener: OnClickListener? = null
) {

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + (onClickListener?.hashCode() ?: 0)
        return result
    }
}

class SingleLineDiffCallback<T : AbstractItemViewModel> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}