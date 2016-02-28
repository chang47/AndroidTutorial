package net.joshchang.josh.week8;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by JoshDesktop on 2/28/2016.
 */
public class StoreActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        int points = 0;
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            points = extra.getInt("points", 0);
        }
        TextView score = (TextView) findViewById(R.id.storePoints);
        score.setText("Points: " + points);

        Button back =  (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}
