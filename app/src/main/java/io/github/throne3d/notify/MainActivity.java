package io.github.throne3d.notify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String STARTED_SHORT_NOTE = "io.github.throne3d.notify.STARTED_SHORT_NOTE";
    public static final String NEW_NOTE = "io.github.throne3d.notify.NEW_NOTE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase.getAppDatabase(this);
    }

    public void writeExtendedNote(View view) {
        // respond to 'write extended note' button
        Intent intent = new Intent(this, WriteNoteActivity.class);
        EditText editText = findViewById(R.id.writeShortEditText);
        String message = editText.getText().toString();
        intent.putExtra(STARTED_SHORT_NOTE, message);
        startActivity(intent);
    }

    public Note saveNoteFromForm() {
        EditText editText = findViewById(R.id.writeShortEditText);
        String message = editText.getText().toString();
        Note note = new Note(null, message);
        note.save();
        return note;
    }

    public void saveShortNote(View view) {
        // respond to 'save note' button
        Intent intent = new Intent(this, DisplayNoteActivity.class);
        Note newNote = saveNoteFromForm();
        intent.putExtra(NEW_NOTE, newNote.getId());
        startActivity(intent);
    }
}
