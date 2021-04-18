package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.ScheduleVo
import com.grsu.schedule_project.model.dbo.ScheduleDbo
import com.grsu.schedule_project.model.dto.ScheduleDto

fun ScheduleDto.toScheduleDbo() = ScheduleDbo(
    days = this.days?.map { dayDto ->
        dayDto.toDayDbo()
    }
)

fun ScheduleDbo.toScheduleVo() = ScheduleVo(
    localId = this.localId,
    days = this.days?.map { dayDbo ->
        dayDbo.toDayVo()
    }
)