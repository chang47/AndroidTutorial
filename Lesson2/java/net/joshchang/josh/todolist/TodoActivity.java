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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class TodoActivity extends AppCompatActivity {
    private AlertDialog.Builder mDialog;
    private ArrayList<String> list;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = new ArrayList<String>();
        ListView lvList = (ListView) findViewById(R.id.lvList);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lvList.setAdapter(adapter);

        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
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
                String word = etText.getText().toString();
                list.add(word);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
