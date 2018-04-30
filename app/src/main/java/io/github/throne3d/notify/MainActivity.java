package io.github.throne3d.notify;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    public static final String STARTED_SHORT_NOTE = "io.github.throne3d.notify.STARTED_SHORT_NOTE";
    public static final String STARTED_SHORT_NOTE_SELECTION_START = "io.github.throne3d.notify.STARTED_SHORT_NOTE_SELECTION_START";
    public static final String STARTED_SHORT_NOTE_SELECTION_END = "io.github.throne3d.notify.STARTED_SHORT_NOTE_SELECTION_END";

    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase.getAppDatabase(this);

        RecyclerView recyclerView = findViewById(R.id.noteRecyclerView);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                getResources().getConfiguration().orientation);
        recyclerView.addItemDecoration(dividerItemDecoration);

        final NoteListAdapter adapter = new NoteListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable final List<Note> notes) {
                Log.d(MainActivity.TAG, "Updating copy of notes.");
                // Update cached copy of notes in adapter.
                adapter.setNotes(notes);
            }
        });
    }

    public void writeExtendedNote(View view) {
        // respond to 'write extended note' button
        Intent intent = new Intent(this, WriteNoteActivity.class);
        EditText editText = findViewById(R.id.writeShortEditText);
        String message = editText.getText().toString();
        int selectionStart = editText.getSelectionStart();
        int selectionEnd = editText.getSelectionEnd();
        intent.putExtra(STARTED_SHORT_NOTE, message);
        intent.putExtra(STARTED_SHORT_NOTE_SELECTION_START, selectionStart);
        intent.putExtra(STARTED_SHORT_NOTE_SELECTION_END, selectionEnd);
        startActivity(intent);
    }

    public Note saveNoteFromForm() {
        final EditText editText = findViewById(R.id.writeShortEditText);
        String message = editText.getText().toString();
        Note note = new Note(null, message);
        noteViewModel.insert(note);
        editText.setText("");
        return note;
    }

    public void saveShortNote(View view) {
        // respond to 'save note' button
        saveNoteFromForm();
    }

    /* view note
        Intent intent = new Intent(this, DisplayNoteActivity.class);
        intent.putExtra(NEW_NOTE, newNote.getId());
        startActivity(intent);
     */
}
