package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.SubGroupVo
import com.grsu.schedule.model.dbo.SubGroupDbo
import com.grsu.schedule.model.dto.SubGroupDto

fun SubGroupDto.toSubGroupDbo() = SubGroupDbo(
    id = this.id,
    title = this.title
)

fun SubGroupDbo.toSubGroupVo() = SubGroupVo(
    localId = this.localId,
    id = this.id,
    title = this.title
)