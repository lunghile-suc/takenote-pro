package com.takenotepro.takenotepro.model;

public class Note {
    private String Title;
    private String Content;
    private String TimeStamp;

    public Note(String title, String content, String timeStamp) {
        Title = title;
        Content = content;
        TimeStamp = timeStamp;
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

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
