package com.grsu.schedule_project.data.source.bookmark

import androidx.lifecycle.LiveData
import com.grsu.schedule_project.data.model.dbo.BookmarkDbo
import com.grsu.schedule_project.data.source.bookmark.local.BookmarkLocalDataSource

class BookmarkRepository(
    private val localDataSource: BookmarkLocalDataSource
) {

    fun getBookmarks(): LiveData<List<BookmarkDbo>> = localDataSource.getBookmarks()

    fun getBookmark(groupId: String?): LiveData<BookmarkDbo?> = localDataSource.getBookmark(groupId)

    suspend fun insertOrDeleteBookmark(bookmarkDbo: BookmarkDbo) {
        localDataSource.insertOrDeleteBookmark(bookmarkDbo)
    }

    suspend fun deleteBookmarks() {
        localDataSource.deleteAll()
    }
}