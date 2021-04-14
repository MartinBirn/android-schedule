package com.grsu.schedule.model.mappers

import com.grsu.schedule.core.view.vo.LessonVo
import com.grsu.schedule.model.dbo.LessonDbo
import com.grsu.schedule.model.dto.LessonDto

fun LessonDto.toLessonDbo() = LessonDbo(
    timeStart = this.timeStart,
    timeEnd = this.timeEnd,
    teacher = this.teacher?.toTeacherDbo(),
    label = this.label,
    type = this.type,
    title = this.title,
    address = this.address,
    room = this.room,
    subGroup = this.subGroup?.toSubGroupDbo()
)

fun LessonDbo.toLessonVo() = LessonVo(
    localId = this.localId,
    timeStart = this.timeStart,
    timeEnd = this.timeEnd,
    teacher = this.teacher?.toTeacherVo(),
    label = this.label,
    type = this.type,
    title = this.title,
    address = this.address,
    room = this.room,
    subGroup = this.subGroup?.toSubGroupVo()
)