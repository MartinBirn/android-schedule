package com.grsu.schedule_project.presentation.common.listadapters

import com.grsu.schedule_project.presentation.common.OnClickListener

class FacultyAdapter : SingleLineAdapter<FacultyItemViewModel>()

data class FacultyItemViewModel(
    override val id: String,
    override val title: String?,
    override val onClickListener: OnClickListener? = null
) : AbstractItemViewModel(id, title, onClickListener)