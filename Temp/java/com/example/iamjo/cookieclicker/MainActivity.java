package com.example.iamjo.cookieclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Button btnPoints;
    public TextView tvPoints;
    public int points;
    public Button btnShop;

    private final String PREF_NAME = "pref";
    private final String POINTS = "points";

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        points = pref.getInt(POINTS, 0);
        btnPoints = (Button) findViewById(R.id.btnPoints);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        btnShop = (Button) findViewById(R.id.btnShop);
        tvPoints.setText("Points: " + points);
    }

    public void shopButton(View v) {
        Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
        intent.putExtra("points", points);
        startActivity(intent);
    }

    public void btnClick(View v) {
        points++;
        tvPoints.setText("Points: " + points);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor edit = pref.edit();
        edit.putInt(POINTS, points);
        edit.commit();
    }
}
