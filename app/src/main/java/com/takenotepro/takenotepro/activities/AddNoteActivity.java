package com.takenotepro.takenotepro.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.takenotepro.takenotepro.R;
import com.takenotepro.takenotepro.model.Note;
import com.takenotepro.takenotepro.utils.Collections;
import com.takenotepro.takenotepro.utils.Toasts;

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

        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        DocumentReference documentReference;
        documentReference = Collections.getCollectionReferenceForNotes().document();

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    // note added successfully
                    Toasts.showToast(AddNoteActivity.this, "Note saved successfully");
                    finish();
                }else {
                    Toasts.showToast(AddNoteActivity.this, "Failed to save note");
                }
            }
        });
    }
}