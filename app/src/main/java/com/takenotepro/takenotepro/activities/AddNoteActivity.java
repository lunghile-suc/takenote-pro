package com.takenotepro.takenotepro.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.takenotepro.takenotepro.R;
import com.takenotepro.takenotepro.model.Note;
import com.takenotepro.takenotepro.utils.Collections;
import com.takenotepro.takenotepro.utils.Toasts;

public class AddNoteActivity extends AppCompatActivity {

    final String TAG = "AddNoteActivity";
    DocumentReference documentReference;

    Button saveNoteBtn;
    ImageView toolbar_menu;
    EditText noteTitleEdtxt;
    EditText noteContentEdtxt;
    String title;
    String content;
    String docId;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        saveNoteBtn = findViewById(R.id.saveNoteBtn);
        toolbar_menu = findViewById(R.id.toolbar_bars);
        noteTitleEdtxt = findViewById(R.id.noteTitle);
        noteContentEdtxt = findViewById(R.id.noteContent);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        docId = getIntent().getStringExtra("docId");

        if (docId != null && !docId.isEmpty()) {
            isEditMode = true;
        }

        noteTitleEdtxt.setText(title);
        noteContentEdtxt.setText(content);

        if (isEditMode) {
            saveNoteBtn.setText("Update Note");
        }

        saveNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });

        toolbar_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(AddNoteActivity.this, toolbar_menu);
                popupMenu.getMenu().add("Delete Note");
                popupMenu.getMenu().add("Logout");
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle() == "Logout") {
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(AddNoteActivity.this, SignInActivity.class));
                            finish();
                            return true;
                        }

                        if (menuItem.getTitle() == "Delete Note") {
                            deleteNote();
                            startActivity(new Intent(AddNoteActivity.this, MainActivity.class));
                            finish();
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
    }

    void saveNote() {
        String noteTitle = noteTitleEdtxt.getText().toString();
        String noteContent = noteContentEdtxt.getText().toString();

        if (noteTitle.isEmpty() && noteTitle == null) {
            noteTitleEdtxt.setHint("Title is missing");
            noteTitleEdtxt.setHintTextColor(getResources().getColor(R.color.black));
            return;
        }

        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        if(isEditMode){
            updateNote();
        }else {
            documentReference = Collections.getCollectionReferenceForNotes().document();
        }

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // note added successfully
                    Toasts.showToast(AddNoteActivity.this, "Note saved successfully");
                    finish();
                } else {
                    Toasts.showToast(AddNoteActivity.this, "Failed to save note");
                }
            }
        });
    }

    void updateNote(){
        String noteTitle = noteTitleEdtxt.getText().toString();
        String noteContent = noteContentEdtxt.getText().toString();

        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        documentReference = Collections.getCollectionReferenceForNotes().document(docId);
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // note added successfully
                    Toasts.showToast(AddNoteActivity.this, "Note deleted successfully");
                    finish();
                } else {
                    Toasts.showToast(AddNoteActivity.this, "Failed to delete note");
                }
            }
        });
    }

    void deleteNote() {
        documentReference = Collections.getCollectionReferenceForNotes().document(docId);
        documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // note added successfully
                    Toasts.showToast(AddNoteActivity.this, "Note deleted successfully");
                    finish();
                } else {
                    Toasts.showToast(AddNoteActivity.this, "Failed to delete note");
                }
            }
        });
    }
}