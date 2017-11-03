package com.mmr.meza.firebasensu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Majedur Rahman on 11/3/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder> {


    ArrayList<Student> students;
    Context context;
    LayoutInflater layoutInflater;

    public ListAdapter(Context c , ArrayList<Student> list) {

        context = c;
        students = list;
        layoutInflater = LayoutInflater.from(c);

    }


    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = layoutInflater.inflate(R.layout.student_item, parent , false);

        ListHolder listHolder = new ListHolder(view);
        return listHolder;
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {

        holder.name.setText(students.get(position).getName());
        holder.Id.setText(students.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ListHolder extends RecyclerView.ViewHolder {

        TextView name ;
        TextView Id;


        public ListHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameTV);
            Id = itemView.findViewById(R.id.IdTV);
        }
    }
}
