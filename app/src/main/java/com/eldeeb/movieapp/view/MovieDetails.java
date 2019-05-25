package com.eldeeb.movieapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.eldeeb.movieapp.R;
import com.eldeeb.movieapp.model.Constants;
import com.eldeeb.movieapp.model.MovieModel;
import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
ImageView backdrop_image;
TextView title,overview,vote_average,release_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        intit();
        MovieModel movieModel= (MovieModel) getIntent().getSerializableExtra(Constants.MovieModel);
        title.setText(movieModel.getTitle());
        vote_average.setText(movieModel.getVote_average()+"");
        release_date.setText(movieModel.getRelease_date());
        overview.setText(movieModel.getOverview());
        Picasso.get()
                .load(Constants.IMAGE_BASE_URL+movieModel.getBackdrop_path()).fit()
                .into(backdrop_image);
    }
    public void intit(){
        backdrop_image=findViewById(R.id.backdrop_image);
        title=findViewById(R.id.title);
        overview=findViewById(R.id.overview);
        vote_average=findViewById(R.id.vote_average);
        release_date=findViewById(R.id.release_date);

    }
}
