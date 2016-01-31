package net.joshchang.josh.todolist;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private ArrayList<String> list;

    // 1) add our new fields
    SoundPool sp;
    int[] soundIds;

    public CustomAdapter(Context context, int resource, ArrayList<String> list) {
        super(context, resource, list);
        this.list = list;

        // 2) Instantiate
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        this.soundIds = new int[1];
        soundIds[0] = sp.load(getContext(), R.raw.boom, 1);

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_adapter, null);
        }

        String word = list.get(position);

        TextView tvText = (TextView) v.findViewById(R.id.tvItem);
        Button btnText = (Button) v.findViewById(R.id.btnItem);

        tvText.setText(word);
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3) play
                sp.play(soundIds[0], 1, 1, 1, 0, 1);
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return v;
    }

}
