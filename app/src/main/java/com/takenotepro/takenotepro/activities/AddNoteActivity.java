package com.takenotepro.takenotepro.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.takenotepro.takenotepro.R;

public class AddNoteActivity extends AppCompatActivity {

    Button saveNoteBtn;
    EditText noteTitleEdtxt;
    EditText noteContentEdtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        saveNoteBtn = findViewById(R.id.saveNoteBtn);
        noteTitleEdtxt = findViewById(R.id.noteTitle);
        noteContentEdtxt = findViewById(R.id.noteContent);

        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    void saveNote(){
        String noteTitle = noteTitleEdtxt.getText().toString();
        String noteContent = noteContentEdtxt.getText().toString();

        if(noteTitle.isEmpty() && noteTitle == null){
            noteTitleEdtxt.setHint("Title is missing");
            noteTitleEdtxt.setHintTextColor(getResources().getColor(R.color.black));
            return;
        }

        
    }
}