package com.grsu.schedule_project.presentation.screen.home.faculties

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.source.faculty.FacultyRepository
import com.grsu.schedule_project.presentation.common.OnClickListener
import com.grsu.schedule_project.presentation.common.listadapters.FacultyItemViewModel
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FacultiesViewModel(
    private val router: ScheduleRouter,
    private val facultyRepository: FacultyRepository,
    private val departmentId: String?
) : ViewModel() {

    private var facultyJob: Job? = null

    private val _snackBar = MutableLiveData<String?>()
    val snackBar: LiveData<String?>
        get() = _snackBar

    private val _spinner = MutableLiveData<Boolean>(true)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _facultyItemViewModelList = MutableLiveData<List<FacultyItemViewModel>?>()
    val facultyItemViewModelList: MutableLiveData<List<FacultyItemViewModel>?>
        get() = _facultyItemViewModelList

    init {
        getFaculties()
    }

    fun retryGetFaculties() {
        facultyJob?.cancel()
        _spinner.value = true
        _facultyItemViewModelList.value = null
        getFaculties()
    }

    private fun getFaculties() {
        facultyJob = viewModelScope.launch {
            when (val facultyResult = facultyRepository.getFaculties()) {
                is RepoResult.Success -> {
                    _facultyItemViewModelList.value = facultyResult.response.map {
                        FacultyItemViewModel(
                            it.localId,
                            it.title,
                            onClickListener = object : OnClickListener {
                                override fun onClick() {
                                    router.navigateTo(
                                        HomeScreens.courseScreen(departmentId, facultyId = it.id)
                                    )
                                }
                            })
                    }
                    _spinner.value = false
                }
                is RepoResult.Failure.NetworkFailure -> _snackBar.value =
                    facultyResult.error.localizedMessage
                is RepoResult.Failure.UnknownFailure -> _snackBar.value =
                    facultyResult.error.localizedMessage
                is RepoResult.Failure.HttpFailure -> _snackBar.value =
                    facultyResult.error.toString()
                is RepoResult.Failure.ApiFailure -> _snackBar.value =
                    facultyResult.error.toString()
                is RepoResult.Failure.DatabaseFailure -> _snackBar.value =
                    facultyResult.error.localizedMessage
            }
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}