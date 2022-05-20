package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class insertGameData extends AppCompatActivity {
    //initialising variables

    EditText editTextGameName;
    EditText editTextPrice;
    EditText editTextDescription;

    Button buttonAddGame;
    Button buttonAllGame;
    Spinner spinnerGameGenre;
    ListView listViewGames;


    //our database reference object
    DatabaseReference databaseGames;
    //a list to store all the games from firebase database
    public List<Game> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_game_data);


        editTextGameName = (EditText) this.findViewById(R.id.editTextGameName);
        buttonAddGame = findViewById(R.id.btnAddGame);
        buttonAllGame = findViewById(R.id.viewAll);
        spinnerGameGenre = (Spinner) this.findViewById(R.id.spinnerGenre);
        editTextPrice = (EditText) this.findViewById(R.id.editTextPrice);
        editTextDescription = (EditText) this.findViewById(R.id.editTextDescription);

        //getting the reference of games node
        databaseGames = FirebaseDatabase.getInstance().getReference("games");
        //list to store games
        games = new ArrayList<>();


        //adding an onclicklistener to button
        buttonAddGame.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
          addGame();
       }
   });

        buttonAllGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),productsList.class);
                startActivity(i);
            }
        });



    }




    public void addGame(){
        //getting the values to save
        String gamename = editTextGameName.getText().toString().trim();
        String genre = spinnerGameGenre.getSelectedItem().toString();
        String gameprice = editTextPrice.getText().toString().trim();
        String gamedescription = editTextDescription.getText().toString().trim();
        //checking if the value is provided
        if (!TextUtils.isEmpty(gamename)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our games
            String id = databaseGames.push().getKey();

            //creating an games Object
            Game game = new Game(id, gamename, genre, gameprice, gamedescription);

            //Saving the game
            databaseGames.child(id).setValue(game);

            //setting edittext to blank again
            editTextGameName.setText("");

            //displaying a success toast
            Toast.makeText(insertGameData.this,"Game added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(insertGameData.this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }






}
