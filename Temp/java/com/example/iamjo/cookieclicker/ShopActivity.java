package com.example.iamjo.cookieclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity {
    public Button btnBack;
    public TextView tvPoints;
    public int points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        btnBack = (Button) findViewById(R.id.btnBack);
        tvPoints = (TextView) findViewById(R.id.tvShopPoints);

        points = 0;
        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            points = extra.getInt("points", 0);
        }

        tvPoints.setText("Points: " + points);
    }

    public void backToMain(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
