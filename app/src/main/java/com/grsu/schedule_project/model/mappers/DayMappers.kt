package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.DayVo
import com.grsu.schedule_project.model.dbo.DayDbo
import com.grsu.schedule_project.model.dto.DayDto

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