package net.joshchang.josh.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ListActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Button btnSignOut = (Button) findViewById(R.id.signOut);
        // setup our FirebaseAuth objects and listeners
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                // gets the user
                FirebaseUser user = firebaseAuth.getCurrentUser();
                // logic to deal with the state the user is in
                if (user != null) {
                    Toast.makeText(ListActivity.this, "you're logged in!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListActivity.this, "you're not logged in", Toast.LENGTH_SHORT).show();
                    goToLogin();
                }
            }
        };
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

    // can't switch activities inside a listener create a seperate method
    // go to our login page if the user is not logged in
    public void goToLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    // method that runs when users click the logout button
    // log out the users from their instance in firebase and sends a notification to
    // the listener
    public void signOut(View view) {
        mAuth.signOut();
    }
}
