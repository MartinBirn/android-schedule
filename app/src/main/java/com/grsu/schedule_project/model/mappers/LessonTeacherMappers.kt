package com.grsu.schedule_project.model.mappers

import com.grsu.schedule_project.core.view.vo.LessonTeacherVo
import com.grsu.schedule_project.model.dbo.LessonTeacherDbo
import com.grsu.schedule_project.model.dto.LessonTeacherDto

fun LessonTeacherDto.toLessonTeacherDbo() = LessonTeacherDbo(
    id = this.id,
    fullname = this.fullname,
    post = this.post
)

fun LessonTeacherDbo.toLessonTeacherVo() = LessonTeacherVo(
    localId = this.localId,
    lessonId = this.lessonId,
    id = this.id,
    fullname = fullname,
    post = this.post
)