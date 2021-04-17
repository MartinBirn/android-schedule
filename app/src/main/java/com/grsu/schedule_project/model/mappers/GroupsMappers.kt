package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.GroupsVo
import com.grsu.schedule_project.model.dbo.GroupsDbo
import com.grsu.schedule_project.model.dto.GroupsDto

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