package net.joshchang.josh.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
    private AlertDialog.Builder mDialog;
    private ArrayList<String> list;
    private ArrayAdapter adapter;
    private MediaPlayer backgroundMusic;
    private SoundPool sp;
    private int soundIds[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<String>();
        ListView lvList = (ListView) findViewById(R.id.lvList);

        adapter = new CustomAdapter(this, R.layout.custom_adapter, list);
        lvList.setAdapter(adapter);

        backgroundMusic = MediaPlayer.create(this, R.raw.goinghigher);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();

        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        soundIds = new int[2];
        soundIds[0] = sp.load(this, R.raw.open, 1);
        soundIds[1] = sp.load(this, R.raw.add, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        backgroundMusic.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        backgroundMusic.start();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            sp.play(soundIds[0], 1, 1, 1, 0, 1);
            createNewDialog();
            mDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNewDialog() {
        mDialog = new AlertDialog.Builder(this);
        final EditText etText = new EditText(this);
        mDialog.setView(etText);
        mDialog.setTitle("Add new Task");
        mDialog.setMessage("Type in your new task!");
        mDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sp.play(soundIds[1], 1, 1, 1, 0, 1);
                String word = etText.getText().toString();
                list.add(word);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
