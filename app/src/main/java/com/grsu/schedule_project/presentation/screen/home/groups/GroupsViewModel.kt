package com.grsu.schedule_project.presentation.screen.home.groups

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_EMPTY
import com.grsu.schedule_project.data.model.snackbarhelpers.SnackBarWrapper
import com.grsu.schedule_project.data.source.group.GroupRepository
import com.grsu.schedule_project.presentation.common.OnClickListener
import com.grsu.schedule_project.presentation.common.listadapters.GroupItemViewModel
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GroupsViewModel(
    private val router: ScheduleRouter,
    private val utils: Utils,
    private val groupRepository: GroupRepository,
    private val departmentId: String?,
    private val facultyId: String?,
    private val courseId: String?
) : ViewModel() {

    private var groupJob: Job? = null

    private val _snackBarWrapper = MutableLiveData<SnackBarWrapper?>()
    val snackBarWrapper: LiveData<SnackBarWrapper?>
        get() = _snackBarWrapper

    private val _spinner = MutableLiveData<Boolean>(true)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _groupItemViewModelList = MutableLiveData<List<GroupItemViewModel>>()
    val groupItemViewModelList: LiveData<List<GroupItemViewModel>>
        get() = _groupItemViewModelList

    init {
        getGroups()
    }

    fun retryGetGroups() {
        groupJob?.cancel()
        getGroups()
    }

    private fun getGroups() {
        if (departmentId == null || facultyId == null || courseId == null) {
            router.backTo(HomeScreens.departmentScreen())
            return
        }
        groupJob = viewModelScope.launch {
            //delete previous results
            groupRepository.deleteGroups()

            when (val groupResult =
                groupRepository.getGroups(departmentId, facultyId, courseId)) {
                is RepoResult.Success -> {
                    if (groupResult.response.isEmpty()) {
                        _snackBarWrapper.value = SnackBarWrapper(
                            snackBar = utils.getStringById(R.string.snack_bar_groups_not_found),
                            snackBarCause = SNACK_BAR_CAUSE_EMPTY
                        )
                    } else {
                        _groupItemViewModelList.value = groupResult.response.map {
                            GroupItemViewModel(
                                it.localId,
                                it.title,
                                onClickListener = object : OnClickListener {
                                    override fun onClick() {
                                        router.navigateTo(
                                            HomeScreens.scheduleScreen(
                                                groupId = it.id,
                                                groupTitle = it.title
                                            )
                                        )
                                    }
                                })
                        }
                    }
                    _spinner.value = false
                }
                is RepoResult.Failure.NetworkFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = groupResult.error.localizedMessage)
                is RepoResult.Failure.UnknownFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = groupResult.error.localizedMessage)
                is RepoResult.Failure.HttpFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = groupResult.error.toString())
                is RepoResult.Failure.ApiFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = groupResult.error.toString())
                is RepoResult.Failure.DatabaseFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = groupResult.error.localizedMessage)
            }
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}