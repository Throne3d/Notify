package io.github.throne3d.notify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String STARTED_SHORT_MESSAGE = "io.github.throne3d.notify.STARTED_SHORT_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void writeExtendedNote(View view) {
        // respond to 'write extended note' button
        Intent intent = new Intent(this, WriteNoteActivity.class);
        EditText editText = findViewById(R.id.writeShortEditText);
        String message = editText.getText().toString();
        intent.putExtra(STARTED_SHORT_MESSAGE, message);
        startActivity(intent);
    }

    public void saveShortNote(View view) {
        // respond to 'save note' button
        // TODO: save
        Intent intent = new Intent(this, DisplayNoteActivity.class);
        EditText editText = findViewById(R.id.writeShortEditText);
        String message = editText.getText().toString();
        intent.putExtra(STARTED_SHORT_MESSAGE, message);
        startActivity(intent);
    }
}
