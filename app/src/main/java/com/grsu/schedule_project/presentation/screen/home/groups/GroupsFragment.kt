package com.grsu.schedule_project.presentation.screen.home.groups

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.grsu.schedule_project.R
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_EMPTY
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_ERROR
import com.grsu.schedule_project.databinding.FragmentGroupsBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.common.RecreateActionListener
import com.grsu.schedule_project.presentation.common.listadapters.GroupAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GroupsFragment : Fragment(R.layout.fragment_groups), BackButtonListener,
    RecreateActionListener {

    companion object {
        private const val DEPARTMENT_ID = "department_id"
        private const val FACULTY_ID = "faculty_id"
        private const val COURSE_ID = "course_id"

        fun getNewInstance(departmentId: String?, facultyId: String?, courseId: String?) =
            GroupsFragment().apply {
                arguments = bundleOf(
                    DEPARTMENT_ID to departmentId,
                    FACULTY_ID to facultyId,
                    COURSE_ID to courseId
                )
            }
    }

    private val viewBinding: FragmentGroupsBinding by viewBinding()

    private val groupsViewModel: GroupsViewModel by viewModel {
        parametersOf(
            arguments?.getString(DEPARTMENT_ID),
            arguments?.getString(FACULTY_ID),
            arguments?.getString(COURSE_ID)
        )
    }
    private val groupAdapter = GroupAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        viewBinding.toolbar.setNavigationOnClickListener {
            groupsViewModel.onBackPressed()
        }
        viewBinding.recyclerView.adapter = groupAdapter
        viewBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(context, RecyclerView.VERTICAL)
        )

        subscribeUi()
    }

    private fun subscribeUi() {
        groupsViewModel.spinner.observe(viewLifecycleOwner) { show ->
            viewBinding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }
        groupsViewModel.snackBarWrapper.observe(viewLifecycleOwner) { snackBarWrapper ->
            if (snackBarWrapper != null && snackBarWrapper.snackBar != null) {
                val text = snackBarWrapper.snackBar.toString()
                var resId: Int = R.string.action_retry
                val action = when (snackBarWrapper.snackBarCause) {
                    SNACK_BAR_CAUSE_ERROR -> {
                        { groupsViewModel.retryGetGroups() }
                    }
                    SNACK_BAR_CAUSE_EMPTY -> {
                        resId = R.string.action_back
                        { groupsViewModel.onBackPressed() }
                    }
                    else -> {
                        { Unit }
                    }
                }
                Snackbar.make(viewBinding.placeSnackBar, text, Snackbar.LENGTH_INDEFINITE)
                    .setAction(resId) { action.invoke() }
                    .show()
            }
        }
        groupsViewModel.groupItemViewModelList.observe(viewLifecycleOwner) { groupItemViewModelList ->
            groupAdapter.submitList(groupItemViewModelList)
        }
    }

    override fun recreate() {
        groupsViewModel.retryGetGroups()
    }

    override fun onBackPressed(): Boolean {
        groupsViewModel.onBackPressed()
        return true
    }
}