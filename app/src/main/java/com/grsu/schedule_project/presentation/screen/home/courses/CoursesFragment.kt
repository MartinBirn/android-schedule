package com.grsu.schedule_project.presentation.screen.home.courses

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grsu.schedule_project.R
import com.grsu.schedule_project.databinding.FragmentCoursesBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import com.grsu.schedule_project.presentation.common.RecreateActionListener
import com.grsu.schedule_project.presentation.common.listadapters.CourseAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CoursesFragment : Fragment(R.layout.fragment_courses), BackButtonListener,
    RecreateActionListener {

    companion object {
        private const val DEPARTMENT_ID = "department_id"
        private const val FACULTY_ID = "faculty_id"

        fun getNewInstance(departmentId: String?, facultyId: String?) = CoursesFragment().apply {
            arguments = bundleOf(
                DEPARTMENT_ID to departmentId,
                FACULTY_ID to facultyId
            )
        }
    }

    private val viewBinding: FragmentCoursesBinding by viewBinding()

    private val coursesViewModel: CoursesViewModel by viewModel {
        parametersOf(
            arguments?.getString(DEPARTMENT_ID),
            arguments?.getString(FACULTY_ID)
        )
    }
    private val courseAdapter = CourseAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        viewBinding.toolbar.setNavigationOnClickListener {
            coursesViewModel.onBackPressed()
        }
        viewBinding.recyclerView.adapter = courseAdapter
        viewBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(context, RecyclerView.VERTICAL)
        )

        subscribeUi()
    }

    private fun subscribeUi() {
        coursesViewModel.spinner.observe(viewLifecycleOwner) { show ->
            viewBinding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }
        coursesViewModel.courseItemViewModelList.observe(viewLifecycleOwner) { courseItemViewModelList ->
            courseAdapter.submitList(courseItemViewModelList)
        }
    }

    override fun recreate() {
        coursesViewModel.retryGetCourses()
    }

    override fun onBackPressed(): Boolean {
        coursesViewModel.onBackPressed()
        return true
    }
}