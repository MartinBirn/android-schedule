package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.dbo.DayDbo
import com.grsu.schedule_project.data.model.dto.DayDto
import com.grsu.schedule_project.data.model.vo.DayVo

fun DayDto.toDayDbo() = DayDbo(
    num = this.num,
    date = this.date
).apply {
    val id = localId
    lessons = this@toDayDbo.lessons?.map { lessonDto ->
        lessonDto.toLessonDbo().apply { dayId = id }
    }
}

fun DayDbo.toDayVo() = DayVo(
    localId = this.localId,
    num = this.num,
    date = this.date,
    lessons = this.lessons?.map { lessonDbo ->
        lessonDbo.toLessonVo()
    }
)