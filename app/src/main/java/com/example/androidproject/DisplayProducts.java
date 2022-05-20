package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayProducts extends AppCompatActivity {

    ImageView display_imageView;
    TextView display_description,display_title;
    Button btn_cart;
    TextView display_price;
    TextView display_details;


    String data1,data2, data3,data4;
    String gameGenre;
    String gamePrice;
    String gameDescription;
    String gameName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products);


        display_imageView = findViewById(R.id.display_imageView);
        display_title = findViewById(R.id.display_title);
        display_description = findViewById(R.id.display_description);
        btn_cart = findViewById(R.id.btn_cart);

        display_price = findViewById(R.id.price);
        display_details = findViewById(R.id.display_details);



        Intent intent = getIntent();
        gameName = intent.getStringExtra("gamename");
        display_title.setText(gameName);
        gameDescription = intent.getStringExtra("gamedescription");
        display_description.setText(gameDescription);
        gamePrice = intent.getStringExtra("gameprice");
        display_price.setText(gamePrice);
        gameGenre = intent.getStringExtra("gamegenre");
        display_details.setText(gameGenre);


        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newactivity();
            }
        });


    }







    public void newactivity(){
        Intent intent = new Intent(this ,Form.class);
        intent.putExtra("gamename",gameName);
        intent.putExtra("gameprice",gamePrice);
        intent.putExtra("gamegenre",gameGenre);
        intent.putExtra("gamedescription",gameDescription);
        startActivity(intent);
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

