package net.joshchang.josh.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<String> {
    private ArrayList<String> list;

    public CustomAdapter(Context context, int resource, ArrayList<String> list) {
        super(context, resource, list);
        // 1) saves our list to our adapter so we can use it in our class
        this.list = list;
    }

    // 2) creates each item in our ListView
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;

        // 3) makes sure that our item exists, if not, we create it
        if (v == null) {
            // 4) Create an inflater to re-create our app, similar to when we
            // were working with Action Bars
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_adapter, null);
        }

        String word = list.get(position);

        // 5) Gets the element of the list inside the view and assigns them to variables
        // so we can use them
        TextView tvText = (TextView) v.findViewById(R.id.tvItem);
        Button btnText = (Button) v.findViewById(R.id.btnItem);

        tvText.setText(word);
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 6) removing still works!
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return v;
    }

}
