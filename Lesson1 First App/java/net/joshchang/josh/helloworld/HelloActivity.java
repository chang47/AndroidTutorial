package net.joshchang.josh.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HelloActivity extends AppCompatActivity {
    // Use fields so you can access your widgets at any point in the code
    private EditText mEtText;
    private Button mBtnShow;
    private TextView mTvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        // 1) Gets access to the widget in the layout via their unique ID's
        mEtText = (EditText) findViewById(R.id.etShowWord);
        mBtnShow = (Button) findViewById(R.id.btnShowWord);
        mTvDisplay = (TextView) findViewById(R.id.tvDisplayText);

        // 3) Sets a listener to run your code whenever someone clicks your button.
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gets the text that the user added  in the Edit Text
                String text = mEtText.getText().toString();

                // only set the TextView to the new word if it's not empty
                if (text.length() != 0) {
                    // sets the textview to be the new text
                    mTvDisplay.setText(text);
                    // empty your Edit Text for convenience
                    mEtText.setText("");
                }
            }
        });
    }
}

