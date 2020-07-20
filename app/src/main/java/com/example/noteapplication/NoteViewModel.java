package com.example.noteapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepositry repositry;
    private LiveData<List<Note>>allnodes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repositry=new NoteRepositry(application);
        allnodes=repositry.getallenodes();
    }
    public void insert(Note note){
        repositry.insert(note);
    }
    public void update(Note note){
        repositry.update(note);
    }
    public void delete(Note note){
        repositry.delete(note);
    }
    public void deleteall(){
        repositry.deleteall();
    }
    public LiveData<List<Note>>getAllnodes(){
        return allnodes;
    }
}
