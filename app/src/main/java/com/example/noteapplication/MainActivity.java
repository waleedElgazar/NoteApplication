package com.example.noteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NoteViewModel noteViewModel;
    TextView name, pir, de;
    RecyclerView recyclerView;
    public static final int edit_request=2;
    final NoteAdapter noteAdapter = new NoteAdapter();
    RecyclerView.ViewHolder viewHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkFirstRun();
        recyclerView = findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(noteAdapter);

        noteViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(NoteViewModel.class);
        noteViewModel.getAllnodes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.setnodes(notes);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.RIGHT) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("are u sure u want to delete all notes ?");
                    builder.setTitle("delete all");
                    builder.setCancelable(false);
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "all noted are deleted ", Toast.LENGTH_SHORT).show();
                            noteViewModel.deleteall();
                        }
                    });
                    builder.setNegativeButton("cancel ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                         //   Toast.makeText(MainActivity.this, "no notes deleted", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.create();
                    builder.show();
                } else {
                    noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                }
            }
        }).attachToRecyclerView(recyclerView);

        noteAdapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivity.this, edit_note.class);
                intent.putExtra(add_note.EXTRA_ID, note.getId());
                intent.putExtra(add_note.EXTRA_TITLE, note.getName());
                intent.putExtra(add_note.EXTRA_DESCRIPTION, note.getDescripyion());
                intent.putExtra(add_note.EXTRA_PRIORITY, note.getPriority());
                noteViewModel.delete(note);
                startActivityForResult(intent,edit_request);
            }
        });

    }

    public void add(View view) {
        Intent intent = new Intent(this, add_note.class);
        startActivity(intent);
    }

    public void edit(View v) {
        pir = findViewById(R.id.priority);
        de = findViewById(R.id.desc);
        name = findViewById(R.id.title);
        String title = name.getText().toString();
        String disc = de.getText().toString();
        int pirioriy = Integer.parseInt(pir.getText().toString());
        Intent intent = new Intent(this, edit_note.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("disc", disc);
        bundle.putInt("pirioriy", pirioriy);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void checkFirstRun() {
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE).getBoolean("isFirstRun", true);
        if (isFirstRun) {
            getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                    .edit()
                    .putBoolean("isFirstRun", false)
                    .apply();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("swape right to delete all notes and left to delete the specific note");
            builder.setTitle("take a look");
            builder.create();
            builder.show();
        }
    }
}
