package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.note.database.AppDatabase;
import com.example.note.database.entity.Note;

public class Activity_Edit extends AppCompatActivity {

    private Toolbar toolbarEdit;
    private EditText titleEdit, descripitonEdit;
    private ImageButton deleteNote;
    private Button saveBtn ;
    private AppDatabase database;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //inisilisasi componet
        toolbarEdit = findViewById(R.id.toolbar);
        titleEdit = findViewById(R.id.titleEdit);
        descripitonEdit = findViewById(R.id.desEdit);
        deleteNote = findViewById(R.id.deleteBtn);
        saveBtn = findViewById(R.id.saveButtonEdit);

        //inisilisasi database
        database = AppDatabase.getInstance(getApplicationContext());

        //get Intent and put data from main activity
        Intent data = getIntent();
        id = data.getIntExtra("id", 0);


        //query data
        Note note = database.noteDao().get(id);

        //set title , description
        titleEdit.setText(note.title);
        descripitonEdit.setText(note.descriptionNote);

        //handle delete button
        deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = database.noteDao().get(id);
                database.noteDao().delete(note);
                finish();
            }
        });

        //update button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleNew = titleEdit.getText().toString();
                String descriptionNew = descripitonEdit.getText().toString();
                database.noteDao().update(id, titleNew,descriptionNew);
                finish();
            }
        });




        //handle back button
        toolbarEdit.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}