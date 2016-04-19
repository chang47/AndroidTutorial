package net.joshchang.josh.cookieclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {
    private int points;
    private TextView tvPoints;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        // Get the data that our Intent passed in
        Bundle extra = getIntent().getExtras();
        points = 0;
        // check if we received valid data
        if (extra != null) {
            // get the points that we stored or else get a default value of 0
            points = extra.getInt("points", 0);
        }

        // Creates and set our TextView to show our points
        tvPoints = (TextView) findViewById(R.id.tvShopPoints);
        tvPoints.setText("Points: " + points);

        // Creates our back button to send us back to MainActivity
        Button back = (Button) findViewById(R.id.btnBack);
    }

    // brings us back to the main activity
    public void backToMain(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
