package com.grsu.schedule_project.presentation.common.listadapters

import com.grsu.schedule_project.presentation.common.OnClickListener

class DepartmentAdapter : SingleLineAdapter<DepartmentItemViewModel>()

data class DepartmentItemViewModel(
    override val id: String,
    override val title: String?,
    override val onClickListener: OnClickListener? = null
) : AbstractItemViewModel(id, title, onClickListener)