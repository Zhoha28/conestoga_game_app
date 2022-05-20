package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class productsList extends AppCompatActivity {
    ListView listViewGames;


    //our database reference object
    DatabaseReference databaseGames;
    //a list to store all the games from firebase database
    public List<Game> games;
    Button btnInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        listViewGames = findViewById(R.id.listViewGame2);

        btnInsert = findViewById(R.id.btnSwitchToInsert);
        //getting the reference of games node
        databaseGames = FirebaseDatabase.getInstance().getReference("games");

        //list to store games
        games = new ArrayList<>();
        listViewGames.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Game game = games.get(i);
                showUpdateDeleteDialog(game.getGameId(), game.getGameName());
                return true;
            }
        });



        listViewGames.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Game game = games.get(i);
                Intent intent = new Intent(getApplicationContext(), DisplayProducts.class);
                intent.putExtra("gamename", game.getGameName());
                intent.putExtra("gameprice", game.getGamePrice());
                intent.putExtra("gamegenre", game.getGameGenre());
                intent.putExtra("gamedescription", game.getGamedescription());

                startActivity(intent);
            }
        });



        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(productsList.this,insertGameData.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseGames.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                games.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Game game = postSnapshot.getValue(Game.class);
                    //adding artist to the list
                    games.add(game);
                }

                //creating adapter
                gamesList gameAdapter = new gamesList(productsList.this, games);
                //attaching adapter to the listview
                listViewGames.setAdapter(gameAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void showUpdateDeleteDialog(final String gameId, String gameName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_game_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final Spinner spinnerGenre = (Spinner) dialogView.findViewById(R.id.spinnerGenres);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateGame);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteGame);
        final EditText editTextPrice = (EditText) dialogView.findViewById(R.id.gameprice);
        final EditText editTextDescription = (EditText) dialogView.findViewById(R.id.Gamedescription);

        dialogBuilder.setTitle(gameName);
        final AlertDialog b = dialogBuilder.create();
        b.show();


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String genre = spinnerGenre.getSelectedItem().toString();
                String price = editTextPrice.getText().toString().trim();
                String description = editTextDescription.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateGame(gameId, name, genre, price, description);
                    b.dismiss();
                }
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteGame(gameId);
                b.dismiss();

            }
        });
    }
    private boolean updateGame(String id, String name, String genre, String price, String description) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("games").child(id);

        //updating artist
        Game game = new Game(id, name, genre, price, description);

        dR.setValue(game);
        Toast.makeText(getApplicationContext(), "Game Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteGame(String id) {
        //getting the specified artist reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("games").child(id);

        //removing artist
        dR.removeValue();


        Toast.makeText(getApplicationContext(), "Game Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    //get which item is selected
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.homepage:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.products:
                Intent intent2= new Intent(this, productsList.class);
                startActivity(intent2);
                return true;

            case R.id.location:
                Intent intent3= new Intent(this, locateMe.class);
                startActivity(intent3);
                return true;
                //default when no case is true
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //infalting the menu resource file
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
