package com.example.note.database.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey
    public int id;

    public String title;

    public String descriptionNote;


}
