package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.GroupVo
import com.grsu.schedule.model.dbo.GroupDbo
import com.grsu.schedule.model.dto.GroupDto

fun GroupDto.toGroupDbo() = GroupDbo(
    id = this.id,
    title = this.title
)

fun GroupDbo.toGroupVo() = GroupVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)