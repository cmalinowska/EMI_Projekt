package com.example.carina.haushaltsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.carina.haushaltsapp.Einkaufsliste.MyShopping;
import com.example.carina.haushaltsapp.Notizen.MyNotes;
import com.example.carina.haushaltsapp.ReggaeFeeling.Corc;

import static com.example.carina.haushaltsapp.R.id.*;

public class HaushaltActivity extends AppCompatActivity {
private Button btnEinkauf, btnAusgaben, btnToDo, btnNotizen, btnFun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haushalt);
        InitializeApp();
    }

    private void InitializeApp() {
        btnEinkauf = (Button) findViewById(button1);
        btnAusgaben = (Button) findViewById(button2);
        btnToDo = (Button) findViewById(button3);
        btnNotizen = (Button) findViewById(button4);
        ImageButton btnFun = (ImageButton) findViewById(button5);

        btnEinkauf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEinkauf();
            }
        });

        btnNotizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNotizen();
            }
        });

        btnFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMusik();
            }
        });

    }


    private void startEinkauf() {
        startActivity(
         new Intent(this, MyShopping.class));
     }


    private void startNotizen() {startActivity(
                new Intent(this, MyNotes.class));
    }

    private void startMusik() {
        startActivity(
                new Intent(this, Corc.class));
    }

}
