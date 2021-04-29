package com.grsu.schedule_project.presentation.screen.home.departments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.source.department.DepartmentRepository
import com.grsu.schedule_project.presentation.common.OnClickListener
import com.grsu.schedule_project.presentation.common.listadapters.DepartmentItemViewModel
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DepartmentsViewModel(
    private val router: ScheduleRouter,
    private val departmentRepository: DepartmentRepository
) : ViewModel() {

    private var departmentJob: Job? = null

    private val _snackBar = MutableLiveData<String?>()
    val snackBar: LiveData<String?>
        get() = _snackBar

    private val _spinner = MutableLiveData<Boolean>(true)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _departmentItemViewModelList = MutableLiveData<List<DepartmentItemViewModel>>()
    val departmentItemViewModelList: LiveData<List<DepartmentItemViewModel>>
        get() = _departmentItemViewModelList

    init {
        getDepartments()
    }

    fun retryGetDepartments() {
        departmentJob?.cancel()
        getDepartments()
    }

    private fun getDepartments() {
        departmentJob = viewModelScope.launch {
            when (val departmentResult = departmentRepository.getDepartments()) {
                is RepoResult.Success -> {
                    _departmentItemViewModelList.value = departmentResult.response.map {
                        DepartmentItemViewModel(
                            it.localId,
                            it.title,
                            onClickListener = object : OnClickListener {
                                override fun onClick() {
                                    router.navigateTo(HomeScreens.facultyScreen(departmentId = it.id))
                                }
                            })
                    }
                    _spinner.value = false
                }
                is RepoResult.Failure.NetworkFailure -> _snackBar.value =
                    departmentResult.error.localizedMessage
                is RepoResult.Failure.UnknownFailure -> _snackBar.value =
                    departmentResult.error.localizedMessage
                is RepoResult.Failure.HttpFailure -> _snackBar.value =
                    departmentResult.error.toString()
                is RepoResult.Failure.ApiFailure -> _snackBar.value =
                    departmentResult.error.toString()
                is RepoResult.Failure.DatabaseFailure -> _snackBar.value =
                    departmentResult.error.localizedMessage
            }
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}