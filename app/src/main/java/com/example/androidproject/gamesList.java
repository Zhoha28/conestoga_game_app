package com.example.androidproject;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class gamesList extends ArrayAdapter<Game> {
    private Activity context;
    List<Game> gameList;

    public gamesList(Activity context, List<Game> gameList) {
        super(context, R.layout.list_layout,gameList);
        this.context = context;
        this.gameList = gameList;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.textViewGenre);

        Game game = gameList.get(position);
        textViewName.setText(game.getGameName());
        textViewGenre.setText(game.getGameGenre());

        return listViewItem;
    }

}

