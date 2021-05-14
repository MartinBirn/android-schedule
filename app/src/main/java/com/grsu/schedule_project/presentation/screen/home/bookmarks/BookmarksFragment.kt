package com.grsu.schedule_project.presentation.screen.home.bookmarks

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grsu.schedule_project.R
import com.grsu.schedule_project.databinding.FragmentBookmarksBinding
import com.grsu.schedule_project.presentation.common.listadapters.BookmarkAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    companion object {
        fun getNewInstance() = BookmarksFragment()
    }

    private val viewBinding: FragmentBookmarksBinding by viewBinding()

    private val bookmarksViewModel: BookmarksViewModel by viewModel()
    private val bookmarkAdapter = BookmarkAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        viewBinding.recyclerView.adapter = bookmarkAdapter
        viewBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(context, RecyclerView.VERTICAL)
        )

        subscribeUi()
    }

    private fun subscribeUi() {
        bookmarksViewModel.bookmarkItemViewModelList.observe(viewLifecycleOwner) { bookmarkItemViewModelList ->
            bookmarkAdapter.submitList(bookmarkItemViewModelList)
        }
    }
}