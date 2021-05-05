package com.grsu.schedule_project.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.grsu.schedule_project.data.model.dbo.BookmarkDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao : BaseDao<BookmarkDbo> {

    @Transaction
    suspend fun insertAndDeletePrevious(vararg bookmarks: BookmarkDbo) {
        deleteAll()
        insert(*bookmarks)
    }

    @Transaction
    suspend fun insertOrDelete(bookmark: BookmarkDbo) {
        if (getBookmark(bookmark.groupId) == null)
            insert(bookmark)
        else
            delete(bookmark)
    }

    @Query("DELETE FROM bookmark")
    suspend fun deleteAll()

    @Query("SELECT * FROM bookmark WHERE groupId = :groupId")
    suspend fun getBookmark(groupId: String): BookmarkDbo?

    @Query("SELECT * FROM bookmark WHERE groupId = :groupId")
    fun get(groupId: String?): Flow<BookmarkDbo?>

    @Query("SELECT * FROM bookmark")
    fun getAll(): Flow<List<BookmarkDbo>>
}