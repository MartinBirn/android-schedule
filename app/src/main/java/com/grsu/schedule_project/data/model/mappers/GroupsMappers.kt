package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.dbo.GroupsDbo
import com.grsu.schedule_project.data.model.dto.GroupsDto
import com.grsu.schedule_project.data.model.vo.GroupsVo

fun GroupsDto.toGroupsDbo() = GroupsDbo(
    items = this.items?.map { groupDto ->
        groupDto.toGroupDbo()
    }
)

fun GroupsDbo.toGroupsVo() = GroupsVo(
    localId = this.localId,
    items = this.items?.map { groupDbo ->
        groupDbo.toGroupVo()
    }
)