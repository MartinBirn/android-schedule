package com.grsu.schedule_project.presentation.screen.home.departments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.grsu.schedule_project.R
import com.grsu.schedule_project.databinding.FragmentDepartmentsBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.common.RecreateActionListener
import com.grsu.schedule_project.presentation.common.listadapters.DepartmentAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DepartmentsFragment : Fragment(R.layout.fragment_departments), BackButtonListener,
    RecreateActionListener {

    companion object {
        fun getNewInstance() = DepartmentsFragment()
    }

    private val viewBinding: FragmentDepartmentsBinding by viewBinding()

    private val departmentsViewModel: DepartmentsViewModel by viewModel()
    private val departmentAdapter = DepartmentAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerView.adapter = departmentAdapter
        viewBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(context, RecyclerView.VERTICAL)
        )

        subscribeUi()
    }

    private fun subscribeUi() {
        departmentsViewModel.spinner.observe(viewLifecycleOwner) { show ->
            viewBinding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }
        departmentsViewModel.snackBar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(viewBinding.placeSnackBar, text, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.action_retry) {
                        departmentsViewModel.retryGetDepartments()
                    }
                    .show()
            }
        }
        departmentsViewModel.departmentItemViewModelList.observe(viewLifecycleOwner) { departmentItemViewModelList ->
            departmentAdapter.submitList(departmentItemViewModelList)
        }
    }

    override fun recreate() {
        departmentsViewModel.retryGetDepartments()
    }

    override fun onBackPressed(): Boolean {
        departmentsViewModel.onBackPressed()
        return true
    }
}