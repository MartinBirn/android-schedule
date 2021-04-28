package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.vo.ScheduleVo
import com.grsu.schedule_project.data.model.dbo.ScheduleDbo
import com.grsu.schedule_project.data.model.dto.ScheduleDto

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