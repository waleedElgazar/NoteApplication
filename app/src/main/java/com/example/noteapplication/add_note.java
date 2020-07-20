package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class add_note extends AppCompatActivity {
    EditText name, de;
    NumberPicker pir;
    NoteViewModel noteViewModel;
    public static final String EXTRA_ID =
            "ccom.example.noteapplication.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.noteapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.noteapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.noteapplication.EXTRA_PRIORITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        noteViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(NoteViewModel.class);
        pir = findViewById(R.id.Priority);
        de = findViewById(R.id.desc);
        name = findViewById(R.id.title);
        pir.setMinValue(1);
        pir.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra(EXTRA_TITLE));
        de.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        pir.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        {
            setTitle("note added");
        }

    }

    public void save(View view) {
        String title = name.getText().toString();
        String disc = de.getText().toString();
        int pirioriy = pir.getValue();
       if(!title.isEmpty()||!disc.isEmpty()){
            noteViewModel.insert(new Note(title, disc, pirioriy));
            Toast.makeText(this, "note saved ", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "sure u enter title and description", Toast.LENGTH_SHORT).show();
        }
    }


}