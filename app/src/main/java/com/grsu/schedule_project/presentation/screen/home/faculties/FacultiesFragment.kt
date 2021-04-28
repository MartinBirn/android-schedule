package com.grsu.schedule_project.presentation.screen.home.faculties

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
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.databinding.FragmentFacultiesBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.common.listadapters.FacultyAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FacultiesFragment : Fragment(R.layout.fragment_faculties), BackButtonListener {

    companion object {
        private const val DEPARTMENT_ID = "department_id"

        fun getNewInstance(departmentId: String?) = FacultiesFragment().apply {
            arguments = bundleOf(DEPARTMENT_ID to departmentId)
        }
    }

    private val viewBinding: FragmentFacultiesBinding by viewBinding()

    private val facultiesViewModel: FacultiesViewModel by viewModel {
        parametersOf(arguments?.getString(DEPARTMENT_ID))
    }
    private val facultyAdapter = FacultyAdapter()

    private val utils: Utils by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.toolbar.title = utils.getStringById(R.string.toolbar_faculties_title)
        viewBinding.recyclerView.adapter = facultyAdapter
        viewBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(context, RecyclerView.VERTICAL)
        )

        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        viewBinding.toolbar.setNavigationOnClickListener {
            facultiesViewModel.onBackPressed()
        }

        subscribeUi()
    }

    private fun subscribeUi() {
        facultiesViewModel.spinner.observe(viewLifecycleOwner) { show ->
            viewBinding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }
        facultiesViewModel.snackBar.observe(viewLifecycleOwner) { text ->
            text?.let {
                Snackbar.make(viewBinding.placeSnackBar, text, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.action_retry) {
                        facultiesViewModel.retryGetFaculties()
                    }
                    .show()
            }
        }
        facultiesViewModel.facultyItemViewModelList.observe(viewLifecycleOwner) { facultyItemViewModelList ->
            facultyAdapter.submitList(facultyItemViewModelList)
        }
    }

    override fun onBackPressed(): Boolean {
        facultiesViewModel.onBackPressed()
        return true
    }
}