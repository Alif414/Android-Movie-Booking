package com.example.moviebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button superBtn;
    private Button actBtn;
    private Button animBtn;

    ArrayList<String> movies = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superBtn = findViewById(R.id.superhero);
        superBtn.setOnClickListener(this);
        actBtn = findViewById(R.id.action);
        actBtn.setOnClickListener(this);
        animBtn = findViewById(R.id.animation);
        animBtn.setOnClickListener(this);

    }

    public  void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        //passing List of movies
        intent.putStringArrayListExtra("movies", movies);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            //Setting list depending on which button is clicked
            case R.id.superhero:
                movies.removeAll(movies);
                movies.add("Aquaman");
                movies.add("Avengers: Infinity War");
                movies.add("Dead Pool 2");
                movies.add("Ant Man and the Wasp");
                break;
            case R.id.action:
                movies.removeAll(movies);
                movies.add("Mission Impossible: Fallout");
                movies.add("Rampage");
                movies.add("Jurassic World: Fallen Kingdom");
                movies.add("Sky Scrapper");
                movies.add("The Meg");
                break;
            case R.id.animation:
                movies.removeAll(movies);
                movies.add("Incredibles 2");
                movies.add("Hotel Transylvania 3");
                movies.add("Peter Rabit");
                break;
        }

        openActivity2();
    }
}