package net.joshchang.josh.cookieclicker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvPoints;
    private Button btn;
    private int points;
    private SharedPreferences pref;
    private Button store;

    // Hard-coded values to prevent confusion with variable types
    private final String PREF_NAME = "pref";
    private final String POINTS = "totalPoints";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("beginner", "onCreate");
        // Basic setup
        tvPoints = (TextView) findViewById(R.id.score);
        btn = (Button) findViewById(R.id.button);
        store = (Button) findViewById(R.id.store);

        // Restoring data with pref
        pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // Get the points that we store in the pref. Otherwise the default value
        points = pref.getInt(POINTS, 0);
        // Sets the text
        tvPoints.setText("Score: " + points);

        // Get our database and initializes our game state
        StoreDbHelper db = new StoreDbHelper(this);
        GameState.init(db.getAllItems());

        // Increment our points and displays it
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ### CHANGE #1
                // Update our points with how much our click is worth
                points += GameState.click;
                tvPoints.setText("Score: " + points);
            }
        });

        // The onclick listener that changes us from our current activity
        // to our StoreActivity
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creates our intent to go to StoreActivity
                Intent i = new Intent(getApplicationContext(), StoreActivity.class);
                // Passes our points to pass along
                // i.putExtra("points", points); // ### CHANGE #2 ELIMINATED

                // ### CHANGE #3 moved our save method to another method
                // save our points to use for the store
                savePoints();

                // Changes our current activity to our StoreActivity
                startActivity(i);
            }
        });
    }

    // Whenever the app is paused we would save our points
    @Override
    protected void onPause() {
        super.onPause();

        /* ### CHANGE #4: move the code to make it re-usable
        // Gets a Editor from our shared preference
        SharedPreferences.Editor edit = pref.edit();
        // Add our points with our editor to be saved
        edit.putInt(POINTS, points);
        // Save our changes
        edit.commit();*/

        // ### CHANGE #5
        // save our points when we pause
        savePoints();
    }

    // CHANGE #6
    // Save our points into our Shared Preference
    public void savePoints() {
        // Gets a Editor from our shared preference
        SharedPreferences.Editor edit = pref.edit();
        // Add our points with our editor to be saved
        edit.putInt(POINTS, points);
        // Save our changes
        edit.commit();
    }
}

