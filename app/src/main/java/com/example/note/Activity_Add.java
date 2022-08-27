package com.example.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.note.database.AppDatabase;

public class Activity_Add extends AppCompatActivity {

   private  EditText inputTitle , inputDescription;
   private Button addButton;
   private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //inisialisasi component
        inputTitle = findViewById(R.id.inputTitle);
        inputDescription = findViewById(R.id.inputDeskripsi);
        addButton = findViewById(R.id.addButtonNewNote);


        //inisialiassi database
        db = AppDatabase.getInstance(getApplicationContext());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.noteDao().insertAll(inputTitle.getText().toString(), inputDescription.getText().toString());
                finish();
            }
        });

    }
}