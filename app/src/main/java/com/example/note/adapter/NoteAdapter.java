package com.example.note.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note.R;
import com.example.note.database.entity.Note;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewAdapter> {
    private List<Note> list;
    private Context context;
    private RecyclerViewInterface recyclerViewInterface;

    //membuat handle click item
    public interface  RecyclerViewInterface {
        void onClick(int position);
    }

    public void setRecyclerViewInterface(RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    public NoteAdapter(Context context , List<Note> list) {
        this.context = context;
        this.list = list;
    }

    //handle kustom list

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_note,parent,false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.labelTitle.setText(list.get(position).title);
        holder.labelDescription.setText(list.get(position).descriptionNote);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends  RecyclerView.ViewHolder {

        TextView labelTitle , labelDescription;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            labelTitle = itemView.findViewById(R.id.labelTitle);
            labelDescription = itemView.findViewById(R.id.labelDescription);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null ) {
                        recyclerViewInterface.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
