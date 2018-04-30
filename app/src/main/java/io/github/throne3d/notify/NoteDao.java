package io.github.throne3d.notify;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY created_at DESC")
    LiveData<List<Note>> getAll();

    @Query("SELECT * FROM notes WHERE id IN (:noteIds)")
    List<Note> loadAllByIds(int[] noteIds);

    @Insert
    long insert(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertOrUpdateAll(Note... notes);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);
}
