package com.grsu.schedule_project.data.source.bookmark.local

import com.grsu.schedule_project.data.database.dao.BookmarkDao
import com.grsu.schedule_project.data.model.dbo.BookmarkDbo
import com.grsu.schedule_project.data.source.bookmark.BookmarkDataSource
import kotlinx.coroutines.flow.Flow

class BookmarkLocalDataSource(private val bookmarkDao: BookmarkDao) : BookmarkDataSource {

    override fun getBookmarks(): Flow<List<BookmarkDbo>> = bookmarkDao.getAll()

    fun getBookmark(groupId: String?): Flow<BookmarkDbo?> = bookmarkDao.get(groupId)

    suspend fun insertBookmark(bookmarkDbo: BookmarkDbo) {
        bookmarkDao.insert(bookmarkDbo)
    }

    suspend fun insertAndDeletePrevious(vararg bookmarks: BookmarkDbo) {
        bookmarkDao.insertAndDeletePrevious(*bookmarks)
    }

    suspend fun insertOrDeleteBookmark(bookmark: BookmarkDbo) {
        bookmarkDao.insertOrDelete(bookmark)
    }

    suspend fun deleteAll() {
        bookmarkDao.deleteAll()
    }
}