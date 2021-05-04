package com.grsu.schedule_project.data.source.bookmark

import androidx.lifecycle.LiveData
import com.grsu.schedule_project.data.model.dbo.BookmarkDbo

interface BookmarkDataSource {

    fun getBookmarks(): LiveData<List<BookmarkDbo>>
}