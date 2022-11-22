package org.techtown.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.Viewholder> {

    ArrayList<Person> items = new ArrayList<Person>();

    OnPersonItemClickListener listener;

    public void addItem(Person item) { items.add(item); }
    public void setItems(ArrayList<Person> items) { this.items = items; }
    public Person getItem(int position) { return items.get(position); }
    public void setItem(int position, Person item) { items.set(position, item); }
    public void setOnItemClickListener(OnPersonItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);
        return new Viewholder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder {

        TextView textView;
        TextView textView2;

        public Viewholder(View itemView, final OnPersonItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null) {
                        listener.onItemClick(Viewholder.this, view, position);
                    }
                }
            });
        }
        public void setItem(Person item) {
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }
    }

}
