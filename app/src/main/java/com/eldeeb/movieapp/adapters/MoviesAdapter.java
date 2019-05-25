package com.eldeeb.movieapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eldeeb.movieapp.R;
import com.eldeeb.movieapp.model.Constants;
import com.eldeeb.movieapp.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private Context context;
    List<MovieModel> moviesList;

    public interface ClickListener{
        public void onClick(MovieModel movieModel);
        public void loadMore();
    }
    ClickListener clickListener;
    public MoviesAdapter(Context c) {
        this.context = c;
        this.moviesList = new ArrayList<>();
        clickListener= (ClickListener) context;
    }
    public void setItems(List<MovieModel> list) {
        moviesList.addAll(list);

    }

    public void clear() {
        moviesList.clear();
       notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = null;
        row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(row);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            Picasso.get()
                .load(Constants.IMAGE_BASE_URL +moviesList.get(i).getPoster_path()).fit()
                .into(viewHolder.thumbnail);
        viewHolder.title.setText(moviesList.get(i).getTitle());
        if (i == moviesList.size() - 1) {
            clickListener.loadMore();

        }

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView thumbnail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            itemView.setOnClickListener(this);
            thumbnail.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            clickListener.onClick(moviesList.get(getAdapterPosition()));
        }
    }
}

