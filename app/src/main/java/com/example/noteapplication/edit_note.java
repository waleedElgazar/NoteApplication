package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class edit_note extends AppCompatActivity {
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
    String ti,des;
    int pirr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
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
        ti=intent.getStringExtra(EXTRA_TITLE);
        name.setText(ti);
        des=intent.getStringExtra(EXTRA_DESCRIPTION);
        de.setText(des);
        pirr=intent.getIntExtra(EXTRA_PRIORITY, 1);
        pir.setValue(pirr);

    }
    public void update(View view) {
        String title = name.getText().toString();
        String disc = de.getText().toString();
        int pirioriy = pir.getValue();
        System.out.println(ti+" "+des);
        noteViewModel.insert(new Note(title, disc, pirioriy));
        Toast.makeText(this, "note edited ", Toast.LENGTH_SHORT).show();
    }
}
