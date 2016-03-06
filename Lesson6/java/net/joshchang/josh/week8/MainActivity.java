package net.joshchang.josh.week8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvPoints;
    private Button btn;
    private Button store;
    private int points;
    private SharedPreferences pref;

    private final String PREF_NAME = "pref";
    private final String POINTS = "totalPoints";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Basic setup
        tvPoints = (TextView) findViewById(R.id.score);
        points = 0;
        btn = (Button) findViewById(R.id.button);
        store = (Button) findViewById(R.id.store);

        // Restoring data with pref
        pref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        points = pref.getInt(POINTS, 0); // get the default value
        tvPoints.setText("Score: " + points);

        //getApplicationContext().deleteDatabase(StoreContract.DATABASE_NAME);
        StoreDbHelper db = new StoreDbHelper(this);
        ItemHolder.init(db.getAllItems());

        Log.d("thenewarray", "size is: " + ItemHolder.list.size());

        // Increment our points
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                points += ItemHolder.click;
                tvPoints.setText("Score: " + points);
                SharedPreferences.Editor edit = pref.edit();
                edit.putInt(POINTS, points);
                edit.commit();
            }
        });

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StoreActivity.class);
                //i.putExtra("points", points);
                startActivity(i);
            }
        });
    }
}
