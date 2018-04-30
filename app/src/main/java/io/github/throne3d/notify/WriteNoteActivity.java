package io.github.throne3d.notify;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class WriteNoteActivity extends AppCompatActivity {
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.STARTED_SHORT_NOTE);
        int selectionStart = intent.getIntExtra(MainActivity.STARTED_SHORT_NOTE_SELECTION_START, 0);
        int selectionEnd = intent.getIntExtra(MainActivity.STARTED_SHORT_NOTE_SELECTION_END, 0);

        // Capture the layout's TextView and set the string as its text
        EditText noteEditText = findViewById(R.id.noteEditBody);
        noteEditText.setText(message);

        noteEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(noteEditText, InputMethodManager.SHOW_FORCED);
        noteEditText.setSelection(selectionStart, selectionEnd);

        saveNoteFromForm();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveNoteFromForm();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveNoteFromForm();
    }

    public Note saveNoteFromForm() {
        final EditText summaryText = findViewById(R.id.noteEditSummary);
        final EditText bodyText = findViewById(R.id.noteEditBody);
        String summary = summaryText.getText().toString();
        String body = bodyText.getText().toString();
        if (note == null) {
            note = new Note(summary, body);
        } else {
            note.setSummary(summary);
            note.setBody(body);
            note.touch();
        }
        note.save();
        return note;
    }
}
