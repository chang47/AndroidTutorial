package net.joshchang.josh.week8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvTotalPoints;
    private TextView tvPoints;
    private Button btn;
    private Button done;
    private Button store;
    private int points;
    private int totalPoints;
    private SharedPreferences pref;

    private final String PREF_NAME = "pref";
    private final String POINTS = "totalPoints";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Basic setup code
        tvTotalPoints = (TextView) findViewById(R.id.highScore);
        tvPoints = (TextView) findViewById(R.id.score);
        points = 0;
        totalPoints = 0;
        btn = (Button) findViewById(R.id.button);
        done = (Button) findViewById(R.id.done);
        store = (Button) findViewById(R.id.store);

        // Restoring data with pref
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        totalPoints = pref.getInt(POINTS, 0); // get the default value
        tvTotalPoints.setText("High Score: " + totalPoints);

        // Increment our points
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points++;
                tvPoints.setText("Score: " + points);
            }
        });

        // save and reset
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (points > totalPoints) {
                    totalPoints = points;
                    tvTotalPoints.setText("High Score: " + totalPoints);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putInt(POINTS, totalPoints);
                    edit.commit();
                }
                points = 0;
                tvPoints.setText("Score: " + points);
            }
        });

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), StoreActivity.class);
                i.putExtra("points", totalPoints);
                startActivity(i);
            }
        });
    }
}
