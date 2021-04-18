package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.LessonVo
import com.grsu.schedule_project.model.dbo.LessonDbo
import com.grsu.schedule_project.model.dto.LessonDto

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