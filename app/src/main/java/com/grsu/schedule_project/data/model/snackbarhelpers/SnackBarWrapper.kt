package com.grsu.schedule_project.data.model.snackbarhelpers

const val SNACK_BAR_CAUSE_ERROR = "snack_bar_cause_error"
const val SNACK_BAR_CAUSE_EMPTY = "snack_bar_cause_empty"

data class SnackBarWrapper(
    var snackBar: String?,
    var snackBarCause: String = SNACK_BAR_CAUSE_ERROR
)