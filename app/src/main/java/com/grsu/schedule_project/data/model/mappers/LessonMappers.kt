package com.grsu.schedule_project.data.model.mappers

import com.grsu.schedule_project.data.model.vo.LessonVo
import com.grsu.schedule_project.data.model.dbo.LessonDbo
import com.grsu.schedule_project.data.model.dto.LessonDto

fun LessonDto.toLessonDbo() = LessonDbo(
    timeStart = this.timeStart,
    timeEnd = this.timeEnd,
    teacher = this.teacher?.toLessonTeacherDbo(),
    label = this.label,
    type = this.type,
    title = this.title,
    address = this.address,
    room = this.room,
    subGroup = this.subGroup?.toSubGroupDbo()
).apply {
    teacher?.lessonId = this.localId
}

fun LessonDbo.toLessonVo() = LessonVo(
    localId = this.localId,
    dayId = this.dayId,
    timeStart = this.timeStart,
    timeEnd = this.timeEnd,
    teacher = this.teacher?.toLessonTeacherVo(),
    label = this.label,
    type = this.type,
    title = this.title,
    address = this.address,
    room = this.room,
    subGroup = this.subGroup?.toSubGroupVo()
)