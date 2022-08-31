package com.takenotepro.takenotepro.model;

import com.google.firebase.Timestamp;

public class Note {
    private String Title;
    private String Content;
    Timestamp timestamp;

    public Note() {
    }

    public Note(String title, String content, String timeStamp) {
        Title = title;
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
