package net.joshchang.josh.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener ;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText username;
    private EditText password;
    private Button signup;
    private Button login;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // simple setup
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPassword);
        signup = (Button) findViewById(R.id.btnSignUp);
        login = (Button) findViewById(R.id.btnLogin);

        // setup our FirebaseAuth objects and listeners
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                // gets the user
                FirebaseUser user = firebaseAuth.getCurrentUser();
                // logic to deal with the state the user is in
                if (user != null) {
                    Toast.makeText(MainActivity.this, "you're logged in!", Toast.LENGTH_SHORT).show();
                    goToHome();
                } else {
                    Toast.makeText(MainActivity.this, "you're not logged in", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    // can't switch activities inside a listener create a seperate method
    // go to our homepage if the user is already logged in
    public void goToHome() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    // Set our listener to our FirebaseAuth object
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    // Remove our listener when we close our app
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    // method that the signup button calls that signs up the user
    // to Firebase
    public void signUp(View view) {
        // get our user's inputs
        String user = username.getText().toString();
        String pass = password.getText().toString();
        Log.d("testme", "button clicked");
        // signup
        mAuth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            // runs when our sign up completes
            @Override
            public void onComplete(Task<AuthResult> task) {
                Log.d("testme", "on complete " + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {

            // runs when our signup fails
            @Override
            public void onFailure(Exception e) {
                Log.d("testme", "on failure " + e.getMessage());
            }
        });
    }

    // method that the signup button calls that logs the user in to their
    // account on Firebase
    public void signIn(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        Log.d("testme", "signin clicked");

        // sign in
        mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(Task<AuthResult> task) {
                Log.d("testme", "on complete " + task.isSuccessful());
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "sign in failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "sign in successful", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            // runs when our sign in fails
            @Override
            public void onFailure(Exception e) {
                Log.d("testme", "sign in failed " + e.getMessage());
            }
        });
    }
}
