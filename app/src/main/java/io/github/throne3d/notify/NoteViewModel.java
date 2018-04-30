package io.github.throne3d.notify;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private AppDatabase database;
    private LiveData<List<Note>> notes;
    public NoteViewModel(Application application) {
        super(application);
        database = AppDatabase.getAppDatabase(application);
        notes = database.noteDao().getAll();
    }

    LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insert(Note note) {
        note.save();
    }
}
