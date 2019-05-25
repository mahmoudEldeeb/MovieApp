package com.eldeeb.movieapp.viewModels;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.eldeeb.movieapp.model.MovieModel;
import com.eldeeb.movieapp.model.Repositry;
import com.eldeeb.movieapp.model.Results;
import com.eldeeb.movieapp.model.database.SearchModel;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainViewModel extends ViewModel {
MutableLiveData<Results>moviesResultsList=new MutableLiveData<>();
public MainViewModel(){

}

public MutableLiveData<Results> getMovies(final String searchWord, int page, final Application application){

    Repositry.getData(searchWord,page).subscribeWith(new SingleObserver<Results>() {
        @Override
        public void onSubscribe(Disposable d) {
        }
        @Override
        public void onSuccess(Results results) {
            /*
            in first page i check if there is result or not
            and insert the search keyword and delete the oldest if more than 10
             */
            if(results.getTotal_results()!=0&&results.getPage()==1){
                Repositry repositry=new Repositry(application);
                repositry.insert(new SearchModel(searchWord));
                repositry.delete();
            }
            moviesResultsList.setValue(results);

        }

        @Override
        public void onError(Throwable e) {

        }
    });
    return moviesResultsList;
}


    public LiveData<List<SearchModel>> getSearchItem( final Application application) {
    return new Repositry(application).getSearchAllItems();

    }
}
