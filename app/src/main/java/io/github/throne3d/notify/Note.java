package io.github.throne3d.notify;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "notes"
        // indices = {}
)
public class Note {
    @PrimaryKey(autoGenerate = true)
    private long id;

    public Note(String summary, String body) {
        setSummary(summary);
        setBody(body);

        createdAt = new Date();
        updatedAt = createdAt;
    }

    private String summary;
    private String body;

    private Date createdAt;
    private Date updatedAt;


    public void touch() {
        updatedAt = new Date();
    }

    public void save() {
        touch();
        SaveNoteTask saveNoteRunnable = new SaveNoteTask();
        saveNoteRunnable.execute(this);
    }


    public long getId() {
        return id;
    }
    public void setId(long newId) {
        id = newId;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String newSummary) {
        summary = newSummary;
    }

    public String getBody() {
        return body;
    }
    public void setBody(String newBody) {
        body = newBody;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date newCreatedAt) {
        createdAt = newCreatedAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date newUpdatedAt) {
        updatedAt = newUpdatedAt;
    }
}
