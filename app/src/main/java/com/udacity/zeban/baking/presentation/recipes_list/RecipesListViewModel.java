package com.udacity.zeban.baking.presentation.recipes_list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.udacity.zeban.baking.data.api.RecipesApi;
import com.udacity.zeban.baking.data.models.Recipe;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

public class RecipesListViewModel extends ViewModel {

    private RecipesApi recipesApi;

    private CompositeDisposable disposable = new CompositeDisposable();

    private MutableLiveData<List<Recipe>> recipes;

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public RecipesListViewModel(RecipesApi recipesApi) {
        this.recipesApi = recipesApi;
    }

    public LiveData<List<Recipe>> getMovies() {
        if (recipes == null) {
            recipes = new MutableLiveData<>();
            recipes.postValue(new ArrayList<>());
            loadRecipes();
        }
        return recipes;
    }

    private void loadRecipes() {
        loading.setValue(true);

        disposable.add(recipesApi.getRecipes()
                .doFinally(() -> loading.postValue(false))
                .subscribe(recipes::postValue, Timber::e));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
