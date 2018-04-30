package io.github.throne3d.notify;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SaveNoteTask extends AsyncTask<Note, Void, List<Long>> {
    private static final String TAG = SaveNoteTask.class.getName();

    protected List<Long> doInBackground(Note... notes) {
        Log.d(SaveNoteTask.TAG,"doInBackground started");
        NoteDao noteDao = AppDatabase.getAppDatabase(null).noteDao();
        List<Long> ids = noteDao.insertOrUpdateAll(notes);
        Log.d(SaveNoteTask.TAG,"notes inserted");
        for (int i=0; i < ids.size(); i++) {
            notes[i].setId(ids.get(i));
        }
        Log.d(SaveNoteTask.TAG,"returned");
        return ids;
    }

    protected void onPostExecute(List<Long> result) {
        Log.d(SaveNoteTask.TAG, "Saved note(s)! IDs: " + result.toString());
    }
}
