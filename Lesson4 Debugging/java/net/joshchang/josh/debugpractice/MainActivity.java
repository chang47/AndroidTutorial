package net.joshchang.josh.debugpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int clicks;
    Button button;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fix #1: initialize the code!
        button = (Button) findViewById(R.id.button);
        // Error #1! Didn't initialize!
        button.setText("Click me!");
        // Error #2: wrong start value
        clicks = 1;
        Log.d("Tag to find", "our logging message");
    }

    public void incrementClick(View view) {
        clicks++;
        Log.d("DebugPractice", "our click is " + clicks);
        // Whenever we hit our button 5 times, we would show a toast notification
        if (clicks % 5 == 0) {
            Toast.makeText(this, "You've clicked your button 5 times!", Toast.LENGTH_SHORT).show();
        }
    }
}
