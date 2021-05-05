package com.grsu.schedule_project.data.source.bookmark

import com.grsu.schedule_project.data.model.dbo.BookmarkDbo
import kotlinx.coroutines.flow.Flow

interface BookmarkDataSource {

    fun getBookmarks(): Flow<List<BookmarkDbo>>
}