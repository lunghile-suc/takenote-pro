package com.takenotepro.takenotepro.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.takenotepro.takenotepro.R;
import com.takenotepro.takenotepro.activities.AddNoteActivity;
import com.takenotepro.takenotepro.activities.MainActivity;
import com.takenotepro.takenotepro.model.Note;
import com.takenotepro.takenotepro.utils.TimestampToString;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder>{

    Context context;

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull Note note) {
        holder.noteTitle.setText(note.getTitle());
        holder.noteContent.setText(note.getContent());
        holder.noteTimstamp.setText(TimestampToString.timestampToString(note.getTimestamp()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddNoteActivity.class);

                String docId = String.valueOf(NoteAdapter.this.getSnapshots().getSnapshot(position));

                intent.putExtra("title", note.getTitle());
                intent.putExtra("content", note.getContent());
                intent.putExtra("docId", docId);

                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item, parent, false);
        return new NoteViewHolder(view);
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView noteTitle;
        TextView noteContent;
        TextView noteTimstamp;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.noteTitle_txt);
            noteContent = itemView.findViewById(R.id.noteContent_txt);
            noteTimstamp = itemView.findViewById(R.id.noteTimestamp);
        }
    }
}
