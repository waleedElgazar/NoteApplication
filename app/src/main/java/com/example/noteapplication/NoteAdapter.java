package com.example.noteapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    List<Note> notes=new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentnote =notes.get(position);
        holder.titletext.setText(currentnote.getName());
        holder.desctext.setText(currentnote.getDescripyion());
        holder.piroitytext.setText(String.valueOf(currentnote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    public void setnodes(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        TextView  titletext;
        TextView  piroitytext;
        TextView desctext;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            titletext= itemView.findViewById(R.id.title);
            piroitytext=itemView.findViewById(R.id.priority);
            desctext=itemView.findViewById(R.id.desc);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
