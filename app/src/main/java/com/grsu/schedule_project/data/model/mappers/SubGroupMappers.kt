package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.vo.SubGroupVo
import com.grsu.schedule_project.data.model.dbo.SubGroupDbo
import com.grsu.schedule_project.data.model.dto.SubGroupDto

fun SubGroupDto.toSubGroupDbo() = SubGroupDbo(
    id = this.id,
    title = this.title
)

fun SubGroupDbo.toSubGroupVo() = SubGroupVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)