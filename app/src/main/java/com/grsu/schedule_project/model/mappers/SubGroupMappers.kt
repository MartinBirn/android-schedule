package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.SubGroupVo
import com.grsu.schedule_project.model.dbo.SubGroupDbo
import com.grsu.schedule_project.model.dto.SubGroupDto

fun SubGroupDto.toSubGroupDbo() = SubGroupDbo(
    id = this.id,
    title = this.title
)

fun SubGroupDbo.toSubGroupVo() = SubGroupVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)