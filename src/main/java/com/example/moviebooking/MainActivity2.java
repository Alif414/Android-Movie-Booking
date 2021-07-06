package com.example.moviebooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView rating, cast, mins, ticket;
    Spinner mSpinner, tSpinner;
    List<String> showTime;
    String[] sTimeArray;
    EditText eText;
    DatePickerDialog picker;
    int num = 0;
    String tickNum;
    int movieImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Getting list from first activity
        ArrayList<String> movies = getIntent().getStringArrayListExtra("movies");
        rating = findViewById(R.id.ratings);
        cast = findViewById(R.id.cast);
        mins = findViewById(R.id.mins);
        tSpinner = findViewById(R.id.timeSpinner);
        ticket = findViewById(R.id.tickNum);

        tickNum = String.valueOf(num);
        ticket.setText(tickNum);

        ImageButton up = (ImageButton) this.findViewById(R.id.upNum);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Incrementing ticket number
                num++;
                tickNum = String.valueOf(num);
                ticket.setText(tickNum);
            }
        });

        ImageButton down = (ImageButton) this.findViewById(R.id.downNum);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reducing number of tickets
                if (num > 0)
                    num--;
                tickNum = String.valueOf(num);
                ticket.setText(tickNum);
            }
        });


        //Setting spinner
        mSpinner = findViewById(R.id.movieSpinner);
        ArrayAdapter<String> movieAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movies);
        movieAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(movieAdapter);
        mSpinner.setOnItemSelectedListener(this);

        //View with a calendar to select from
        eText=(EditText) findViewById(R.id.calendar);
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        eText.setText(day + "/" + (month + 1) + "/" + year);

        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // date picker dialog
                picker = new DatePickerDialog(MainActivity2.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year); //Current date set
                            }
                        }, year, month, day);
                //Can only pick today and the next 3 days
                picker.getDatePicker().setMinDate(System.currentTimeMillis());
                picker.getDatePicker().setMaxDate(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 3));
                picker.show();
            }
        });

        Button bookButton = (Button) findViewById(R.id.booking);
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Setting data to string variable
                String movieName = mSpinner.getSelectedItem().toString();
                String date = eText.getText().toString();
                String time = tSpinner.getSelectedItem().toString();
                String tickNum = ticket.getText().toString();
                Booking mBooking = new Booking(movieName, date, time, tickNum, movieImage);

                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("Booking", mBooking);

                if(tickNum.equals("0")) {
                    Toast.makeText(getBaseContext(), "You did not select your number of tickets", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    startActivity(intent);
                }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selMovie = parent.getItemAtPosition(position).toString();
        String[] allMovies = {"Aquaman", "Avengers: Infinity War", "Dead Pool 2", "Ant Man and the Wasp",
                "Mission Impossible: Fallout", "Rampage", "Jurassic World: Fallen Kingdom", "Sky Scrapper", "The Meg", "Incredibles 2",
                "Hotel Transylvania 3", "Peter Rabit"};
        String[] allRatings = {"7.5", "9.0", "8.3", "8.5", "7.2", "6.5", "7.5", "7.0", "8.2", "7.2", "7.5", "6.4"};
        String[] allCast = {"Jason Momoa", "Chris Hemsworth", "Ryan Reynolds", "Paul Rudd", "Tom Cruise, Rebecca Ferguson",
                "Dwayne Johnson", "Chris Pratt, Bryce Dallas", "Dwayne Johnson, Neve Campbell", "Jason Statham, Ruby Rose",
                "Holly Hunter", "Andy Samberg", "James Corden"};
        String[] allMins = {"180", "150", "120", "120", "180", "150", "120", "120", "120", "90", "120", "120"};

        String[] tAqua = {"11:30 am", "12:00 pm", "1:40 pm", "5:30 pm", "7:30 pm"};
        String[] tAvengers = {"12:00 pm", "2:30 pm", "3:30 pm", "7:45 pm", "8:30 pm"};
        String[] tPool = {"11:00 am", "1:30 pm", "7:00 pm", "8:30 pm"};
        String[] tAnt = {"10:15 am", "12:20 pm", "3:30 pm", "7:15 pm", "9:30 pm"};
        String[] tMI = {"10:30 am", "12:00 pm", "1:00 pm", "3:30 pm"};
        String[] tRampage = {"9:30 am", "12:15 pm", "2:00 pm", "4:00 pm"};
        String[] tJurassic = {"11:40 am", "1:30 pm", "7:00 pm", "9:00 pm"};
        String[] tSky = {"9:30 am", "11:00 pm", "1:15 pm", "6:30 pm", "10:00 pm"};
        String[] tMeg = {"10:30 am", "11:00 pm", "1:30 pm", "7:00 pm", "9:00 pm"};
        String[] tInc = {"10:00 am", "1:45 pm", "3:30 pm"};
        String[] tHotel = {"12:00 pm", "1:00 pm", "3:30 pm", "4:30pm"};
        String[] tPete = {"11:15 am", "1:30 pm", "3:00 pm", "7:30 pm"};

        int iAqua = R.drawable.aquaman;
        int iAvengers = R.drawable.infinity;
        int iPool = R.drawable.deadpool;
        int iAnt = R.drawable.antman;
        int iMI = R.drawable.fallout;
        int iRamp = R.drawable.rampage;
        int iJurassic = R.drawable.jurassic;
        int iSky = R.drawable.sky_scrapper;
        int iMeg = R.drawable.meg;
        int iInc = R.drawable.incredibles_2;
        int iHotel = R.drawable.hotel;
        int iPete = R.drawable.peter;

        int[] allImg = {iAqua, iAvengers, iPool, iAnt, iMI, iRamp, iJurassic, iSky, iMeg, iInc, iHotel, iPete};

        String[][] showArrays = {tAqua, tAvengers, tPool, tAnt, tMI, tRampage, tJurassic, tSky, tMeg,
        tInc, tHotel, tPete};

        //Setting text based on the movie
        for( int i = 0; i < allMovies.length; i++ )
            if(selMovie.equals(allMovies[i])){
                rating.setText("Ratings: " + allRatings[i]);
                cast.setText("Main Casts: " + allCast[i]);
                mins.setText(allMins[i] + " mins");
                sTimeArray = showArrays[i];
                movieImage = allImg[i];
            }

        showTime = Arrays.asList(sTimeArray);

        //Setting spinner for time
        ArrayAdapter<String> showAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, showTime);
        showAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tSpinner.setAdapter(showAdapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}