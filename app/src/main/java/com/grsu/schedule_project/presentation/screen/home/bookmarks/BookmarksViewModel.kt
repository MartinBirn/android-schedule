package com.grsu.schedule_project.presentation.screen.home.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.source.bookmark.BookmarkRepository
import com.grsu.schedule_project.presentation.common.OnClickListener
import com.grsu.schedule_project.presentation.common.listadapters.BookmarkItemViewModel

class BookmarksViewModel(
    private val router: ScheduleRouter,
    private val utils: Utils,
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    private val _bookmarks = bookmarkRepository.getBookmarks()
    private val _bookmarkItemViewModelList = MediatorLiveData<List<BookmarkItemViewModel>>().apply {
        addSource(_bookmarks) { bookmarkDboList ->
            value = bookmarkDboList.map { bookmarkDbo ->
                BookmarkItemViewModel(
                    id = bookmarkDbo.localId,
                    title = bookmarkDbo.groupTitle ?: "",
                    onClickListener = object : OnClickListener {
                        override fun onClick() {
                            router.openSchedule(
                                bookmarkDbo.groupId,
                                bookmarkDbo.groupTitle
                            )
                        }
                    }
                )
            }
        }
    }
    val bookmarkItemViewModelList: LiveData<List<BookmarkItemViewModel>>
        get() = _bookmarkItemViewModelList
}