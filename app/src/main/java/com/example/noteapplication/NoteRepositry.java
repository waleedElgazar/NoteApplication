package com.example.noteapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class NoteRepositry {
    private NoteDao noteDao;
    private LiveData<List<Note>> allnodes;

    public NoteRepositry(Application application){
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        noteDao =noteDatabase.noteDao();
        allnodes=noteDao.showAll();
    }

    public void insert(Note note){
        new InsertNoteAcyncTask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAcyncTask(noteDao).execute(note);
    }
    public void update(Note note){
        new UpdateNoteAcyncTask(noteDao).execute(note);
    }
    public void deleteall(){
        new DeleteAllNoteAcyncTask(noteDao).execute();
    }
    public LiveData<List<Note>> getallenodes(){
        return allnodes;
    }
    private static class InsertNoteAcyncTask extends AsyncTask <Note,Void,Void>{
        private NoteDao noteDao;
        private InsertNoteAcyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAcyncTask extends AsyncTask <Note,Void,Void>{
        private NoteDao noteDao;
        private UpdateNoteAcyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAcyncTask extends AsyncTask <Note,Void,Void>{
        private NoteDao noteDao;
        private DeleteNoteAcyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNoteAcyncTask extends AsyncTask <Void,Void,Void>{
        private NoteDao noteDao;
        private DeleteAllNoteAcyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }


}
