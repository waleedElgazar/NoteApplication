package com.example.noteapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
class Note {
    private String name;
    private String descripyion;
    private int priority;
    @PrimaryKey(autoGenerate = true)
    int id;
    public Note(String name, String descripyion, int priority) {
        this.name = name;
        this.descripyion = descripyion;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescripyion() {
        return descripyion;
    }

    public int getPriority() {
        return priority;
    }
}
