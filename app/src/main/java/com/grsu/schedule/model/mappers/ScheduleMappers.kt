package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.ScheduleVo
import com.grsu.schedule.model.dbo.ScheduleDbo
import com.grsu.schedule.model.dto.ScheduleDto

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