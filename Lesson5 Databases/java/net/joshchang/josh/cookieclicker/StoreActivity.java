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
import android.widget.Toast;

public class StoreActivity extends AppCompatActivity{
    private StoreDbHelper db;
    private int points;
    private TextView tvUpgrade1;
    private TextView tvUpgrade2;
    private SharedPreferences pref;
    private TextView score;
    private Button btnUpgrade1;
    private Button btnUpgrade2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // Instead of passing our points through intents we get our points directly from
        // shared preference
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        points =  pref.getInt("totalPoints", 0);

        // Create an instance of our freshly implemented DB!
        db = new StoreDbHelper(this);

        // Get our UI from our layout
        score = (TextView) findViewById(R.id.storePoints);
        tvUpgrade1 = (TextView) findViewById(R.id.tvUpgrade1);
        tvUpgrade2 = (TextView) findViewById(R.id.tvUpgrade2);
        btnUpgrade1 = (Button) findViewById(R.id.btnUpgrade1);
        btnUpgrade2 = (Button) findViewById(R.id.btnUpgrade2);

        // Set up our starting text by using the values of the next level of our upgrades
        score.setText("Points: " + points);
        tvUpgrade1.setText("Get +" +GameState.list.get(0).click * (GameState.list.get(0).lvl + 1) + " clicks!");
        tvUpgrade2.setText("Get +" +GameState.list.get(1).click * (GameState.list.get(1).lvl + 1)+ " clicks!");
        btnUpgrade1.setText(GameState.list.get(0).name + ": Cost " + GameState.list.get(0).cost * (GameState.list.get(0).lvl + 1) );
        btnUpgrade2.setText(GameState.list.get(1).name + ": Cost " + GameState.list.get(1).cost * (GameState.list.get(1).lvl  + 1));

        // On click listener for when the user buys the first upgrade
        btnUpgrade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pre-calculate our cost value
                int cost = GameState.list.get(0).cost * (GameState.list.get(0).lvl + 1);
                // If we can purchase the upgrade we make the purchase otherwise we make a Toast notification
                if (points >= cost) {
                    // subtract the cost
                    points -= cost;
                    // moves up our upgrade level
                    GameState.list.get(0).lvl++;
                    // update our upgrade 1 text to reflect the next level
                    btnUpgrade1.setText(GameState.list.get(0).name + ": Cost " + GameState.list.get(0).cost * (GameState.list.get(0).lvl + 1));
                    tvUpgrade1.setText("Get +" + GameState.list.get(0).click * (GameState.list.get(0).lvl + 1) + " clicks!");
                    // update our left over points
                    score.setText("Points: " + points);
                } else {
                    Toast.makeText(getApplicationContext(), "You can't afford it!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // On click listener for when the user buys the second upgrade
        btnUpgrade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pre-calculate our cost value
                int cost = GameState.list.get(1).cost * (GameState.list.get(1).lvl + 1);
                // If we can purchase the upgrade we make the purchase otherwise we make a Toast notification
                if (points >= cost) {
                    // subtract the cost
                    points -= cost;
                    // moves up our upgrade level
                    GameState.list.get(1).lvl++;
                    // update our upgrade 1 text to reflect the next level
                    btnUpgrade2.setText(GameState.list.get(1).name + ": Cost " + GameState.list.get(1).cost * (GameState.list.get(1).lvl + 1));
                    tvUpgrade2.setText("Get +" + GameState.list.get(1).click * (GameState.list.get(1).lvl + 1) + " clicks!");
                    // update our left over points
                    score.setText("Points: " + points);
                } else {
                    Toast.makeText(getApplicationContext(), "You can't afford it!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button back =  (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (StoreItem item : GameState.list) {
                    db.updateItem(item);
                }
                // update your click counter
                GameState.init(GameState.list);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                SharedPreferences.Editor edit = pref.edit();
                edit.putInt("totalPoints", points);
                edit.commit();
            }
        });

    }
}
