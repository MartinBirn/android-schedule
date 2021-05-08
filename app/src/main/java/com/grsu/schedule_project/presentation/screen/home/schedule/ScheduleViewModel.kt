package com.grsu.schedule_project.presentation.screen.home.schedule

import android.text.style.ClickableSpan
import android.view.View
import androidx.lifecycle.*
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.dbo.BookmarkDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.mappers.toLessonVo
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_EMPTY
import com.grsu.schedule_project.data.model.snackbarhelpers.SnackBarWrapper
import com.grsu.schedule_project.data.source.bookmark.BookmarkRepository
import com.grsu.schedule_project.data.source.schedule.ScheduleRepository
import com.grsu.schedule_project.presentation.common.listadapters.DateListItem
import com.grsu.schedule_project.presentation.common.listadapters.LessonListItem
import com.grsu.schedule_project.presentation.common.listadapters.ScheduleListItem
import com.grsu.schedule_project.presentation.common.utils.SpanDataObject
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ScheduleViewModel(
    private val router: ScheduleRouter,
    private val utils: Utils,
    private val scheduleRepository: ScheduleRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val groupId: String?,
    private val groupTitle: String?
) : ViewModel() {

    private var scheduleJob: Job? = null

    private val _snackBarWrapper = MutableLiveData<SnackBarWrapper?>()
    val snackBarWrapper: LiveData<SnackBarWrapper?>
        get() = _snackBarWrapper

    private val _spinner = MutableLiveData<Boolean>(true)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _scheduleItemViewModelList = MutableLiveData<List<ScheduleListItem>?>()
    val scheduleItemViewModelList: LiveData<List<ScheduleListItem>?>
        get() = _scheduleItemViewModelList

    val bookmark: LiveData<BookmarkDbo?> = bookmarkRepository.getBookmark(groupId).asLiveData()

    var dateStart: String? = null
    var dateEnd: String? = null

    init {
        getSchedule()
    }

    fun retryGetSchedule() {
        scheduleJob?.cancel()
        _scheduleItemViewModelList.value = null
        _spinner.value = true
        getSchedule()
    }

    fun addBookmark() {
        viewModelScope.launch {
            if (groupId != null) {
                bookmarkRepository.insertOrDeleteBookmark(
                    bookmark.value ?: BookmarkDbo(groupId = groupId, groupTitle = groupTitle)
                )
            }
        }
    }

    private fun getSchedule() {
        if (groupId == null) {
            router.backTo(HomeScreens.departmentScreen())
            return
        }
        scheduleJob = viewModelScope.launch {
            when (val scheduleResult =
                scheduleRepository.getGroupSchedule(groupId, dateStart, dateEnd)) {
                is RepoResult.Success -> {
                    if (scheduleResult.response.isEmpty()) {
                        _snackBarWrapper.value = SnackBarWrapper(
                            snackBar = utils.getStringById(R.string.snack_bar_schedule_not_found),
                            snackBarCause = SNACK_BAR_CAUSE_EMPTY
                        )
                    } else {
                        _scheduleItemViewModelList.value = scheduleResult.response.map { dayDbo ->
                            val adapterList: ArrayList<ScheduleListItem> =
                                ArrayList<ScheduleListItem>()
                            //add Date item to adapter list
                            adapterList.add(
                                DateListItem(
                                    localId = dayDbo.localId,
                                    dayOfWeekNum = dayDbo.num,
                                    date = dayDbo.date
                                )
                            )
                            //add Lesson item to adapter list
                            dayDbo.lessons?.let { lessonDboList ->
                                val lessons = lessonDboList.map { lessonDbo ->
                                    val lessonVo = lessonDbo.toLessonVo()
                                    LessonListItem(
                                        localId = lessonVo.localId,
                                        timeStart = lessonVo.timeStart,
                                        timeEnd = lessonVo.timeEnd,
                                        teacher = lessonVo.teacher,
                                        label = lessonVo.label,
                                        lessonType = lessonVo.type,
                                        title = lessonVo.title,
                                        address = lessonVo.address,
                                        room = lessonVo.room,
                                        subGroup = lessonVo.subGroup,
                                        clickableSpan = SpanDataObject(
                                            span = object : ClickableSpan() {
                                                override fun onClick(widget: View) {
                                                    router.navigateTo(
                                                        HomeScreens.teacherScreen(teacherId = lessonVo.teacher?.id)
                                                    )
                                                }
                                            },
                                            spanString = lessonVo.teacher?.fullname ?: ""
                                        )
                                    )
                                }
                                adapterList.addAll(lessons)
                            }
                            adapterList
                        }.flatten()
                    }
                    _spinner.value = false
                }
                is RepoResult.Failure.NetworkFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = scheduleResult.error.localizedMessage)
                is RepoResult.Failure.UnknownFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = scheduleResult.error.localizedMessage)
                is RepoResult.Failure.HttpFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = scheduleResult.error.toString())
                is RepoResult.Failure.ApiFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = scheduleResult.error.toString())
                is RepoResult.Failure.DatabaseFailure -> _snackBarWrapper.value =
                    SnackBarWrapper(snackBar = scheduleResult.error.localizedMessage)
            }
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}