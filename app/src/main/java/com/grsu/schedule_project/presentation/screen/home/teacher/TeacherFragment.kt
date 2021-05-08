package com.grsu.schedule_project.presentation.screen.home.teacher

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_EMPTY
import com.grsu.schedule_project.data.model.snackbarhelpers.SNACK_BAR_CAUSE_ERROR
import com.grsu.schedule_project.databinding.FragmentTeacherBinding
import com.grsu.schedule_project.presentation.common.BackButtonListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TeacherFragment : Fragment(R.layout.fragment_teacher), BackButtonListener {

    companion object {
        private const val TEACHER_ID = "teacher_id"

        fun getNewInstance(teacherId: String?) = TeacherFragment().apply {
            arguments = bundleOf(TEACHER_ID to teacherId)
        }
    }

    private val viewBinding: FragmentTeacherBinding by viewBinding()

    private val teacherViewModel: TeacherViewModel by viewModel {
        parametersOf(arguments?.getString(TEACHER_ID))
    }

    private val utils: Utils by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        viewBinding.toolbar.title = utils.getStringById(R.string.toolbar_teacher_title)
        viewBinding.toolbar.setNavigationOnClickListener {
            teacherViewModel.onBackPressed()
        }

        subscribeUi()
    }

    private fun subscribeUi() {
        teacherViewModel.spinner.observe(viewLifecycleOwner) { show ->
            viewBinding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
            viewBinding.elementsGroup.visibility = if (show) View.GONE else View.VISIBLE
        }
        teacherViewModel.snackBarWrapper.observe(viewLifecycleOwner) { snackBarWrapper ->
            if (snackBarWrapper != null && snackBarWrapper.snackBar != null) {
                val text = snackBarWrapper.snackBar.toString()
                var resId: Int = R.string.action_retry
                val action = when (snackBarWrapper.snackBarCause) {
                    SNACK_BAR_CAUSE_ERROR -> {
                        { teacherViewModel.retryGetTeachers() }
                    }
                    SNACK_BAR_CAUSE_EMPTY -> {
                        resId = R.string.action_back
                        { teacherViewModel.onBackPressed() }
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
        teacherViewModel.teacherVo.observe(viewLifecycleOwner) { teacherVo ->
            if (teacherVo != null) {
                val fullname = "${teacherVo.name} ${teacherVo.surname} ${teacherVo.patronym}"
                viewBinding.teacherFullName.text = fullname
                viewBinding.teacherPost.text = teacherVo.post
                viewBinding.emailValue.text = teacherVo.email
            }
        }
    }

    override fun onBackPressed(): Boolean {
        teacherViewModel.onBackPressed()
        return true
    }
}