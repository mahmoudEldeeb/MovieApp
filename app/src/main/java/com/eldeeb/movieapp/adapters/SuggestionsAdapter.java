package com.eldeeb.movieapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eldeeb.movieapp.R;

public class SuggestionsAdapter extends CursorAdapter {
    public SuggestionsAdapter(Context context, Cursor cursor)
    {
        super(context, cursor, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView searchItem = (TextView) view.findViewById(R.id.srech_text);
        searchItem.setText(cursor.getString(1));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.search_item, parent, false);
        return view;
    }
}
