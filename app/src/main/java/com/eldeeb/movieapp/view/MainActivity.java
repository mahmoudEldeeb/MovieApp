package com.eldeeb.movieapp.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.database.MatrixCursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;

import com.eldeeb.movieapp.R;
import com.eldeeb.movieapp.adapters.SuggestionsAdapter;
import com.eldeeb.movieapp.adapters.MoviesAdapter;
import com.eldeeb.movieapp.model.Constants;
import com.eldeeb.movieapp.model.MovieModel;
import com.eldeeb.movieapp.model.Results;
import com.eldeeb.movieapp.model.database.SearchModel;
import com.eldeeb.movieapp.viewModels.MainViewModel;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.ClickListener{
SearchView search_view;
SuggestionsAdapter mSuggestionsAdapter;
RecyclerView movies_res;
MoviesAdapter moviesAdapter;
MainViewModel mainViewModel;
ImageView no_result;
     MatrixCursor cursor;
     String searchWord;
     static int pageNumber=1;
     static int total_page=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_view=findViewById(R.id.search_view);
        movies_res=findViewById(R.id.movies_res);
        no_result=findViewById(R.id.no_result);


        RecyclerView.LayoutManager layoutManager= new GridLayoutManager(this,2);
        moviesAdapter=new MoviesAdapter(this);
        movies_res.setLayoutManager(layoutManager);
        movies_res.setAdapter(moviesAdapter);
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        getSearchItems();

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchWord=query;
                pageNumber=1;

                //call .clear to clear the list
                moviesAdapter.clear();

                search();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                 return false;
            }
        });
        search_view.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int i) {
                search_view.setQuery(cursor.getString(i),true);
                return false;
            }

            @Override
            public boolean onSuggestionClick(int i) {
                search_view.setQuery(cursor.getString(1),true);
                return false;
            }
        });
    }
    /*
    this method get last 10 search
    ////////
     */
    public void getSearchItems(){
        mainViewModel.getSearchItem(getApplication()).observe(this, new Observer<List<SearchModel>>() {
            @Override
            public void onChanged(@Nullable List<SearchModel> searchModels) {
                String[] columns = new String[]{"_id", "text"};
                cursor = new MatrixCursor(columns);
                mSuggestionsAdapter = new SuggestionsAdapter(MainActivity.this, cursor);
                search_view.setSuggestionsAdapter(mSuggestionsAdapter);
                Collections.reverse(searchModels);
                for(int i=0;i<searchModels.size();i++){
                     Object[] temp = new Object[]{0, searchModels.get(i).getSearchItem()};
                    cursor.addRow(temp);
                }
                mSuggestionsAdapter.notifyDataSetChanged();
            }
        });
    }
    /*
    this method called when press search
    i use viewmodel and livedata
     */
private void search(){
        mainViewModel.getMovies(searchWord,pageNumber,getApplication()).observe(this, new Observer<Results>() {
            @Override
            public void onChanged(@Nullable Results results) {
                if(results.getResults().size()==0)
                    no_result.setVisibility(View.VISIBLE);
                else {
                    total_page=results.getTotal_pages();
                    no_result.setVisibility(View.GONE);
                    moviesAdapter.setItems(results.getResults());
//                    this runnable because we can not add item while scroll
                    movies_res.post(new Runnable()
                    {
                        @Override
                        public void run() {
                            moviesAdapter.notifyDataSetChanged();
                        }
                    });
                }
                }
        });
}

/*
this method called when click on item transfer the data to movies details activity
///
 */

    @Override
    public void onClick(MovieModel movieModel) {
        Intent intent=new Intent(MainActivity.this,MovieDetails.class);
        intent.putExtra(Constants.MovieModel,movieModel);
        startActivity(intent);
    }
    @Override
    public void loadMore() {
        if(total_page!=pageNumber)
        {
            pageNumber++;
            search();
        }
    }
}
