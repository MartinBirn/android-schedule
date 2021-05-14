package com.grsu.schedule_project.common.navigation.commands

import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.FragmentScreen

data class SwitchTabCommand(
    val screen: FragmentScreen,
    val tag: String
) : Command