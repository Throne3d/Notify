package io.github.throne3d.notify;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

public class SaveNoteTask extends AsyncTask<Note, Integer, ArrayList<Long>> {
    private static final String TAG = SaveNoteTask.class.getName();

    protected ArrayList<Long> doInBackground(Note... notes) {
        ArrayList<Long> ids = new ArrayList();
        for (int i=0; i < notes.length; i++) {
            Note note = notes[i];
            long id = AppDatabase.getAppDatabase(null).noteDao().insert(note);
            note.setId(id);
            ids.add(id);
            publishProgress(i);
            if (isCancelled()) break;
        }
        return ids;
    }

    protected void onPostExecute(ArrayList<Long> result) {
        // TODO: don't use a static instance of the main activity to interact with the UI?
        // TODO: investigate constant ID 0
        Log.d(SaveNoteTask.TAG, "Saved note(s)! IDs: " + result.toString());
    }
}
