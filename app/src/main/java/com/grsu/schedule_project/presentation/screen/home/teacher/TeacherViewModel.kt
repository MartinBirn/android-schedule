package com.grsu.schedule_project.presentation.screen.home.teacher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.mappers.toTeacherVo
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_EMPTY
import com.grsu.schedule_project.data.model.snackbarhelpers.SnackBarWrapper
import com.grsu.schedule_project.data.model.vo.TeacherVo
import com.grsu.schedule_project.data.source.teacher.TeacherRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TeacherViewModel(
    private val router: ScheduleRouter,
    private val utils: Utils,
    private val teacherRepository: TeacherRepository,
    private val teacherId: String?
) : ViewModel() {

    private var teacherJob: Job? = null

    private val _snackBarWrapper = MutableLiveData<SnackBarWrapper?>()
    val snackBarWrapper: LiveData<SnackBarWrapper?>
        get() = _snackBarWrapper

    private val _spinner = MutableLiveData<Boolean>(true)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _teacherVo = MutableLiveData<TeacherVo?>()
    val teacherVo: LiveData<TeacherVo?>
        get() = _teacherVo

    init {
        getTeachers()
    }

    fun retryGetTeachers() {
        teacherJob?.cancel()
        _spinner.value = true
        _teacherVo.value = null
        getTeachers()
    }

    private fun getTeachers() {
        if (teacherId == null) {
            router.exit()
            return
        }
        teacherJob = viewModelScope.launch {
            when (val teacherResult = teacherRepository.getTeachers(teacherId)) {
                is RepoResult.Success -> {
                    if (teacherResult.response.isEmpty()) {
                        _snackBarWrapper.value = SnackBarWrapper(
                            snackBar = utils.getStringById(R.string.snack_bar_teacher_not_found),
                            snackBarCause = SNACK_BAR_CAUSE_EMPTY
                        )
                    } else {
                        _teacherVo.value = teacherResult.response[0].toTeacherVo()
                        _spinner.value = false
                    }
                }
                is RepoResult.Failure.NetworkFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = teacherResult.error.localizedMessage)
                is RepoResult.Failure.UnknownFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = teacherResult.error.localizedMessage)
                is RepoResult.Failure.HttpFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = teacherResult.error.toString())
                is RepoResult.Failure.ApiFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = teacherResult.error.toString())
                is RepoResult.Failure.DatabaseFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = teacherResult.error.localizedMessage)
            }
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}