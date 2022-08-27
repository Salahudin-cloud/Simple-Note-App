package com.example.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.note.adapter.NoteAdapter;
import com.example.note.database.AppDatabase;
import com.example.note.database.entity.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rec;
    private ImageButton addBtn;
    private AppDatabase database;
    private NoteAdapter noteAdapter;
    private List<Note> listNote = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi component
        addBtn = findViewById(R.id.addBtn);
        rec = findViewById(R.id.listRec);

        //inisialisasi database
        database = AppDatabase.getInstance(getApplicationContext());

        //handle list item
        listNote.clear();
        listNote.addAll(database.noteDao().getAll());
        noteAdapter = new NoteAdapter(getApplicationContext(), listNote);


        //handle click item
        noteAdapter.setRecyclerViewInterface(new NoteAdapter.RecyclerViewInterface() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this , Activity_Edit.class);
                intent.putExtra("id", listNote.get(position).id);
                startActivity(intent);
            }
        });


        //membuat list
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL, false);
        rec.setLayoutManager(layoutManager);
        rec.setAdapter(noteAdapter);


      addBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            startActivity(new Intent(MainActivity.this , Activity_Add.class));
          }
      });

    }

    //refresh list


    @Override
    protected void onStart() {
        super.onStart();
        listNote.clear();
        listNote.addAll(database.noteDao().getAll());
        noteAdapter.notifyDataSetChanged();
    }
}