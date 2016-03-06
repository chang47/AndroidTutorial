package net.joshchang.josh.week8;

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

/**
 * Created by JoshDesktop on 2/28/2016.
 */
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
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        points =  pref.getInt("totalPoints", 0);
        db = new StoreDbHelper(this);
        score = (TextView) findViewById(R.id.storePoints);
        score.setText("Points: " + points);
        Log.d("thenewarray", "current point is " + points);
        tvUpgrade1 = (TextView) findViewById(R.id.tvUpgrade1);
        tvUpgrade2 = (TextView) findViewById(R.id.tvUpgrade2);

        btnUpgrade1 = (Button) findViewById(R.id.btnUpgrade1);
        btnUpgrade2 = (Button) findViewById(R.id.btnUpgrade2);

        tvUpgrade1.setText("Get +" +ItemHolder.list.get(0).click * (ItemHolder.list.get(0).lvl + 1) + " clicks!");
        tvUpgrade2.setText("Get +" +ItemHolder.list.get(1).click * (ItemHolder.list.get(1).lvl + 1)+ " clicks!");

        btnUpgrade1.setText(ItemHolder.list.get(0).name + ": Cost " + ItemHolder.list.get(0).cost * (ItemHolder.list.get(0).lvl + 1) );
        btnUpgrade2.setText(ItemHolder.list.get(1).name + ": Cost " + ItemHolder.list.get(1).cost * (ItemHolder.list.get(1).lvl  + 1));

        btnUpgrade1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cost = ItemHolder.list.get(0).cost * (ItemHolder.list.get(0).lvl + 1);
                if (points >= cost) {
                    points -= cost;
                    Log.d("thenewarray", "current point is " + points);
                    ItemHolder.list.get(0).lvl++;
                    btnUpgrade1.setText(ItemHolder.list.get(0).name + ": Cost " + ItemHolder.list.get(0).cost * (ItemHolder.list.get(0).lvl + 1));
                    tvUpgrade1.setText("Get +" + ItemHolder.list.get(0).click * (ItemHolder.list.get(0).lvl + 1) + " clicks!");
                    score.setText("Points: " + points);
                } else {
                    Toast.makeText(getApplicationContext(), "You can't afford it!", Toast.LENGTH_SHORT).show();
                }

             }
        });

        btnUpgrade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cost = ItemHolder.list.get(1).cost * (ItemHolder.list.get(1).lvl + 1);
                if (points >= cost) {
                    Log.d("thenewarray", "current point is " + points);
                    points -= cost;
                    ItemHolder.list.get(1).lvl++;
                    btnUpgrade2.setText(ItemHolder.list.get(1).name + ": Cost " + ItemHolder.list.get(1).cost * (ItemHolder.list.get(1).lvl + 1));
                    tvUpgrade2.setText("Get +" + ItemHolder.list.get(1).click * (ItemHolder.list.get(1).lvl + 1) + " clicks!");
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
                for (StoreItem item : ItemHolder.list) {
                    db.updateItem(item);
                }
                // update your click counter
                ItemHolder.init(ItemHolder.list);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                SharedPreferences.Editor edit = pref.edit();
                edit.putInt("totalPoints", points);
                edit.commit();
            }
        });

    }
}
