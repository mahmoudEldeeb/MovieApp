package com.eldeeb.movieapp.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.eldeeb.movieapp.R;
import com.eldeeb.movieapp.model.database.DatabaseMoviess;
import com.eldeeb.movieapp.model.database.SearchDao;
import com.eldeeb.movieapp.model.database.SearchModel;
import com.eldeeb.movieapp.model.network.RetrofitConnection;

import java.security.PublicKey;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class Repositry{

    private SearchDao searchDao;
    public Repositry(Application application){
        DatabaseMoviess db = DatabaseMoviess.getDatabase(application);
        searchDao= db.searchDao();

    }
    public static Single<Results> getData(String searchWord, int page){
       return RetrofitConnection.getClient().getMovies(Constants.API_KEY,searchWord,page)
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread());
    }

    public  LiveData<List<SearchModel>> getSearchAllItems() {
        return searchDao.getSearchItems();
    }
   /*
   this method to save search keyword
    */
    public void insert (SearchModel searchModel) {
        new insertAsyncTask(searchDao).execute(searchModel);

    }
   /*
   this method to delete oldest search key word
    */
    public void delete(){

        new deleteAsyncTask(searchDao).execute();

    }
    private  class insertAsyncTask extends AsyncTask<SearchModel, Void, Void> {

        private SearchDao mAsyncTaskDao;

        insertAsyncTask(SearchDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SearchModel... params) {
            try {

                mAsyncTaskDao.insert(params[0]);

            }
            catch (Exception e){

            }
            return null;
        }
    }
    private class deleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private SearchDao mAsyncTaskDao;

        deleteAsyncTask(SearchDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {

                mAsyncTaskDao.delete();

            }
            catch (Exception e){

            }
            return null;
        }
    }
}
