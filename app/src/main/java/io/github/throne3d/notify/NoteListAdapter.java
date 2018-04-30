package io.github.throne3d.notify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {
    class NoteViewHolder extends RecyclerView.ViewHolder {
        private final TextView noteItemSummary;
        private final TextView noteItemBody;

        private NoteViewHolder(View itemView) {
            super(itemView);
            noteItemSummary = itemView.findViewById(R.id.noteItemSummary);
            noteItemBody = itemView.findViewById(R.id.noteItemBody);
        }

        public void setNote(Note current) {
            String summary = current.getSummary();
            if (summary == null || summary.isEmpty()) {
                noteItemSummary.setLines(0);
                noteItemBody.setLines(2);
            } else {
                noteItemSummary.setLines(1);
                noteItemBody.setLines(1);
            }
            noteItemSummary.setText(current.getSummary());
            noteItemBody.setText(current.getBody());
        }

        public void displayEmptyText() {
            noteItemSummary.setText("No notes");
            noteItemBody.setText("");
        }
    }

    private final LayoutInflater inflater;
    private List<Note> notes; // Cached copy of notes

    NoteListAdapter(Context context) { inflater = LayoutInflater.from(context); }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_note_item, parent, false);

        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        if (notes != null) {
            Note current = notes.get(position);
            holder.setNote(current);
        } else {
            // Covers the case of data not being ready yet.
            holder.displayEmptyText();
        }
    }

    void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (notes != null)
            return notes.size();
        else return 0;
    }
}
