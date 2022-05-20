package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {


    //declaring variables
    ViewFlipper v_flipper;
    Button btnact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating logic for the button
        
        //referencing button with variable
        btnact = findViewById(R.id.btnact);

        //setting onclick listener
        btnact.setOnClickListener(new View.OnClickListener() {
            @Override

            //this will call function
            public void onClick(View v) {
                actswitch();
            }
        });

        //declaring array which stores images
        int images[] = {R.drawable.pubgp,R.drawable.codp,R.drawable.gtap};

        //referencing view flipper with variable
        v_flipper = findViewById(R.id.v_flipper);

        //using for loop to slide image and it will call function
        for(int image: images)
        {
            flipperImages(image);
        }



    }

    //this function will decide the behaviour of image slider
    public void flipperImages(int image)
    {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(3000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_out_right);
        v_flipper.setOutAnimation(this,android.R.anim.slide_in_left);
    }

    //this function will be called when user will click on shop now button
    public void actswitch() {
        Intent intent = new Intent(this, productsList.class);
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
              Intent intent2 = new Intent(MainActivity.this, productsList.class);
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
