package ru.samsung.itschool.dbgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ElementViewHolder> {

    private int numberItems;
    private DBManager dbManager;
    private ArrayList<Result> data;

    public Adapter(int numberItems){
        this.numberItems = numberItems;
    }

    @Override
    public ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        dbManager = DBManager.getInstance(context);
        data = dbManager.getAllResults();
        int layoutId = R.layout.element;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutId, parent, false);
        ElementViewHolder viewHolder = new ElementViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ElementViewHolder holder, int position) {
        Result result = data.get(position);
        String str = result.name + " -- " + result.score;
        if (result.score >= 500){
            holder.playerView.setTextColor(Color.rgb(60, 170, 60));
        }
        else{
            holder.playerView.setTextColor(Color.rgb(255, 36, 0));
        }
        holder.bind(str);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class ElementViewHolder extends RecyclerView.ViewHolder {

        TextView playerView;

        public ElementViewHolder(View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.item);
        }

        void bind(String text) {
            playerView.setText(text);
        }
    }
}
