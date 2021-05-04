package com.grsu.schedule_project.common.navigation.commands

import com.github.terrakok.cicerone.Command

class OpenSchedule(
    val groupId: String?,
    val groupTitle: String?
) : Command