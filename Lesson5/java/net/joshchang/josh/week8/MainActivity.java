package net.joshchang.josh.week8;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvHighscore;
    private TextView tvScore;
    private Button btn;
    private Button done;
    private int score;
    private int highScore;
    private SharedPreferences pref;

    private final String PREF_NAME = "pref";
    private final String HIGH_SCORE = "highScore";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Basic setup code
        tvHighscore = (TextView) findViewById(R.id.highScore);
        tvScore = (TextView) findViewById(R.id.score);
        score = 0;
        highScore = 0;
        btn = (Button) findViewById(R.id.button);
        done = (Button) findViewById(R.id.done);

        // Restoring data with pref
        pref = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        highScore = pref.getInt(HIGH_SCORE, 0); // get the default value
        tvHighscore.setText("High Score: " + highScore);

        // Increment our score
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                tvScore.setText("Score: " + score);
            }
        });

        // save and reset
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (score > highScore) {
                    highScore = score;
                    tvHighscore.setText("High Score: " + highScore);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putInt(HIGH_SCORE, highScore);
                    edit.commit();
                }
                score = 0;
                tvScore.setText("Score: " + score);
            }
        });
    }
}
