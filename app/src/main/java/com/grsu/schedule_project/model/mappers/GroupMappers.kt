package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.GroupVo
import com.grsu.schedule_project.model.dbo.GroupDbo
import com.grsu.schedule_project.model.dto.GroupDto

fun GroupDto.toGroupDbo() = GroupDbo(
    id = this.id,
    title = this.title
)

fun GroupDbo.toGroupVo() = GroupVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)