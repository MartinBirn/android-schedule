package com.grsu.schedule_project.data.source.bookmark

import com.grsu.schedule_project.data.model.dbo.BookmarkDbo
import com.grsu.schedule_project.data.source.bookmark.local.BookmarkLocalDataSource
import kotlinx.coroutines.flow.Flow

class BookmarkRepository(
    private val localDataSource: BookmarkLocalDataSource
) {

    fun getBookmarks(): Flow<List<BookmarkDbo>> = localDataSource.getBookmarks()

    fun getBookmark(groupId: String?): Flow<BookmarkDbo?> = localDataSource.getBookmark(groupId)

    suspend fun insertOrDeleteBookmark(bookmarkDbo: BookmarkDbo) {
        localDataSource.insertOrDeleteBookmark(bookmarkDbo)
    }

    suspend fun deleteBookmarks() {
        localDataSource.deleteAll()
    }
}