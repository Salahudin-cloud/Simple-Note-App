package com.example.note.database.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.note.database.entity.Note;
import  java.util.List;


@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("INSERT INTO note (title,descriptionNote ) VALUES (:title , :descriptionNote)")
    void insertAll(String title , String descriptionNote);

    @Query("UPDATE  note SET title = :title , descriptionNote = :descriptionNote WHERE id = :id")
    void update(int id , String title ,  String descriptionNote);

    @Query("SELECT * from note WHERE id = :id")
    Note get(int id);

    @Delete
    void delete(Note note);
}
