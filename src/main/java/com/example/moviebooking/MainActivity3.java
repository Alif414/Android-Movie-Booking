package com.example.moviebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView movie, sched, ticks;
    ImageView moviePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Getting details of selected movie
        Intent intent = getIntent();
        Booking booking = intent.getParcelableExtra("Booking");

        movie = findViewById(R.id.movieName);
        sched = findViewById(R.id.schedule);
        ticks = findViewById(R.id.tickets);
        moviePic = findViewById(R.id.movieImg);

        //Setting text and images
        movie.setText(booking.getMovie());
        sched.setText(booking.getDate() + ", " + booking.getTime());
        ticks.setText("# of tickets: " + booking.getTicket());
        moviePic.setBackgroundResource(booking.getImage());
    }
}