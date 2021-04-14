package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.GroupsVo
import com.grsu.schedule.model.dbo.GroupsDbo
import com.grsu.schedule.model.dto.GroupsDto

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