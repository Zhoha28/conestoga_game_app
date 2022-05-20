package com.example.androidproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.*;
import android.content.*;

public class Form extends AppCompatActivity {

    //declaring variables
    TextView title;
    String gameName;
    Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_form);

        //referencing variables to desired buttons
        title = findViewById(R.id.productname);
        checkoutButton = findViewById(R.id.checkout);

        //setting up onclick listener on button
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            //this will call function names switchToThankYouPage
            {
                switchToThankYouPage();
            }
        });

        //This will call functions named below
        getData();
        title.setText(gameName);

    }


    //this function will be called when user clicks on button and it will use the data1 and display_iamgeview
    // which we had created in another java class
    private void getData(){
        if(getIntent().hasExtra("gamename"))
        {
            Intent intent = getIntent();
            //using getstringextra to store image in a variable
            gameName = "Buy " + intent.getStringExtra("gamename");




        }else {
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
        }
    }



    //this function will switch user to thank you page
    public void switchToThankYouPage() {
        Intent intent = new Intent(this, ThankYou.class);
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