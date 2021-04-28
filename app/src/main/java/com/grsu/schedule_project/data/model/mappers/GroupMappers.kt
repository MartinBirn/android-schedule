package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.dbo.GroupDbo
import com.grsu.schedule_project.data.model.dto.GroupDto
import com.grsu.schedule_project.data.model.vo.GroupVo

fun GroupDto.toGroupDbo() = GroupDbo(
    id = this.id,
    title = this.title
)

fun GroupDbo.toGroupVo() = GroupVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)