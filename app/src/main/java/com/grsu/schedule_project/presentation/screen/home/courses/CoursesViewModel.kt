package com.grsu.schedule_project.presentation.screen.home.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.presentation.common.OnClickListener
import com.grsu.schedule_project.presentation.common.listadapters.CourseItemViewModel
import com.grsu.schedule_project.presentation.screen.home.HomeScreens
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val COURSE_FIRST_NUMBER = "1"
private const val COURSE_SECOND_NUMBER = "2"
private const val COURSE_THIRD_NUMBER = "3"
private const val COURSE_FOURTH_NUMBER = "4"
private const val COURSE_FIFTH_NUMBER = "5"
private const val COURSE_SIXTH_NUMBER = "6"

class CoursesViewModel(
    private val router: ScheduleRouter,
    private val utils: Utils,
    private val departmentId: String?,
    private val facultyId: String?
) : ViewModel() {

    private var courseJob: Job? = null

    private val _spinner = MutableLiveData<Boolean>(true)
    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _courseItemViewModelList = MutableLiveData<List<CourseItemViewModel>>()
    val courseItemViewModelList: LiveData<List<CourseItemViewModel>>
        get() = _courseItemViewModelList

    init {
        getCourses()
    }

    private fun getCourses() {
        courseJob = viewModelScope.launch {
            val courseNumberList = listOf(
                COURSE_FIRST_NUMBER,
                COURSE_SECOND_NUMBER,
                COURSE_THIRD_NUMBER,
                COURSE_FOURTH_NUMBER,
                COURSE_FIFTH_NUMBER,
                COURSE_SIXTH_NUMBER
            )
            val courseTitleList = listOf(
                utils.getStringById(R.string.course_first_title),
                utils.getStringById(R.string.course_second_title),
                utils.getStringById(R.string.course_third_title),
                utils.getStringById(R.string.course_fourth_title),
                utils.getStringById(R.string.course_fifth_title),
                utils.getStringById(R.string.course_sixth_title)
            )
            _courseItemViewModelList.value = courseNumberList.mapIndexed { idx, value ->
                CourseItemViewModel(
                    courseId = value,
                    courseTitle = courseTitleList[idx],
                    onCourseItemClickListener = object : OnClickListener {
                        override fun onClick() {
                            router.navigateTo(
                                HomeScreens.groupScreen(departmentId, facultyId, courseId = value)
                            )
                        }
                    }
                )
            }
            _spinner.value = false
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}