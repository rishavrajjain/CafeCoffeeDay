package com.example.android.cafecoffeeday;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        Intent i = getIntent();
        String a= i.getStringExtra("priceMessage");




        Toast.makeText(this,a,Toast.LENGTH_SHORT).show();

    }



}
