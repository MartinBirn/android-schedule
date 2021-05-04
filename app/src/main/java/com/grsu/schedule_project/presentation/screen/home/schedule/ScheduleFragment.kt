package com.grsu.schedule_project.presentation.screen.home.schedule

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.utils.DATE_API_REQUEST_PATTERN
import com.grsu.schedule_project.common.utils.DateUtils
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_EMPTY
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_ERROR
import com.grsu.schedule_project.databinding.FragmentScheduleBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.common.listadapters.ScheduleAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val OFFSET_DAY_BEFORE = -1
private const val OFFSET_WEEK_LATER = 5

class ScheduleFragment : Fragment(R.layout.fragment_schedule), BackButtonListener {

    companion object {
        private const val GROUP_ID = "group_id"
        private const val GROUP_TITLE = "group_title"

        fun getNewInstance(groupId: String?, groupTitle: String?) = ScheduleFragment().apply {
            arguments = bundleOf(
                GROUP_ID to groupId,
                GROUP_TITLE to groupTitle
            )
        }
    }

    private val viewBinding: FragmentScheduleBinding by viewBinding()

    private val scheduleViewModel: ScheduleViewModel by viewModel {
        parametersOf(arguments?.getString(GROUP_ID), arguments?.getString(GROUP_TITLE))
    }
    private val scheduleAdapter = ScheduleAdapter()

    private val utils: Utils by inject()
    private val dateUtils: DateUtils by inject()

    private lateinit var scheduleMenu: Menu
    private lateinit var dateRangePicker: MaterialDatePicker<Pair<Long, Long>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        viewBinding.toolbar.title = arguments?.getString(GROUP_TITLE)
        viewBinding.toolbar.setNavigationOnClickListener {
            scheduleViewModel.onBackPressed()
        }
        viewBinding.recyclerView.adapter = scheduleAdapter
        viewBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(context, RecyclerView.VERTICAL)
        )

        setHasOptionsMenu(true)

        subscribeUi()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        viewBinding.toolbar.inflateMenu(R.menu.schedule_app_bar_menu)
        scheduleMenu = viewBinding.toolbar.menu

        scheduleMenu.findItem(R.id.addToBookmarksAppBarItem)
            .setOnMenuItemClickListener {
                scheduleViewModel.addBookmark()
                true
            }
        scheduleMenu.findItem(R.id.dateRangePickerAppBarItem).setOnMenuItemClickListener {
            val dateFormat = dateUtils.getDateFormatInstance(DATE_API_REQUEST_PATTERN)

            val yesterday = dateUtils.getTodayOffsetInMillis(OFFSET_DAY_BEFORE)
            val weekLater = dateUtils.getTodayOffsetInMillis(OFFSET_WEEK_LATER)

            val start: Long = scheduleViewModel.dateStart?.let { dateStart ->
                dateFormat.parse(dateStart)?.time
            } ?: yesterday
            val end: Long = scheduleViewModel.dateEnd?.let { dateEnd ->
                dateFormat.parse(dateEnd)?.time
            } ?: weekLater

            dateRangePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText(utils.getStringById(R.string.select_dates))
                .setSelection(Pair(start, end))
                .build()

            dateRangePicker.addOnPositiveButtonClickListener {
                scheduleViewModel.dateStart = dateFormat.format(it.first)
                scheduleViewModel.dateEnd = dateFormat.format(it.second)
                scheduleViewModel.retryGetSchedule()
            }

            dateRangePicker.show(childFragmentManager, "date")
            true
        }
        scheduleMenu.findItem(R.id.refreshAppBarItem).setOnMenuItemClickListener {
            scheduleViewModel.retryGetSchedule()
            true
        }

        scheduleViewModel.bookmark.observe(viewLifecycleOwner) { bookmarkDbo ->
            if (bookmarkDbo == null)
                scheduleMenu.findItem(R.id.addToBookmarksAppBarItem)
                    .setIcon(R.drawable.ic_baseline_bookmark_border_24)
            else
                scheduleMenu.findItem(R.id.addToBookmarksAppBarItem)
                    .setIcon(R.drawable.ic_baseline_bookmark_24)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun subscribeUi() {
        scheduleViewModel.spinner.observe(viewLifecycleOwner) { show ->
            viewBinding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }
        scheduleViewModel.snackBarWrapper.observe(viewLifecycleOwner) { snackBarWrapper ->
            if (snackBarWrapper != null && snackBarWrapper.snackBar != null) {
                val text = snackBarWrapper.snackBar.toString()
                val resId: Int
                val action = when (snackBarWrapper.snackBarCause) {
                    SNACK_BAR_CAUSE_ERROR -> {
                        resId = R.string.action_retry
                        { scheduleViewModel.retryGetSchedule() }
                    }
                    SNACK_BAR_CAUSE_EMPTY -> {
                        resId = R.string.action_back
                        { scheduleViewModel.onBackPressed() }
                    }
                    else -> throw RuntimeException(utils.getStringById(R.string.snack_bar_undefined_error))
                }
                Snackbar.make(viewBinding.placeSnackBar, text, Snackbar.LENGTH_INDEFINITE)
                    .setAction(resId) { action.invoke() }
                    .show()
            }
        }
        scheduleViewModel.scheduleItemViewModelList.observe(viewLifecycleOwner) { facultyItemViewModelList ->
            scheduleAdapter.submitList(facultyItemViewModelList)
        }
    }

    override fun onBackPressed(): Boolean {
        scheduleViewModel.onBackPressed()
        return true
    }
}