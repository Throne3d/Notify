package io.github.throne3d.notify;

import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

public class SaveNoteTask extends AsyncTask<Note, Integer, ArrayList<Integer>> {
    public AppDatabase db;

    protected ArrayList<Integer> doInBackground(Note... notes) {
        ArrayList<Integer> ids = new ArrayList();
        for (int i=0; i < notes.length; i++) {
            Note note = notes[i];
            db.noteDao().insert(note);
            ids.add(note.getId());
            publishProgress(i);
            if (isCancelled()) break;
        }
        return ids;
    }

    protected void onPostExecute(ArrayList<Integer> result) {
        // TODO: don't use a static instance of the main activity to interact with the UI?
        // TODO: investigate constant ID 0
        Toast.makeText(MainActivity.instance.getApplicationContext(), "Saved note(s)! IDs: " + result.toString(), Toast.LENGTH_LONG).show();
    }
}
