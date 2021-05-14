package com.grsu.schedule_project.common.navigation.commands

import com.github.terrakok.cicerone.Command

class RestartActivity(
    vararg val extraPair: Pair<String, String>
) : Command