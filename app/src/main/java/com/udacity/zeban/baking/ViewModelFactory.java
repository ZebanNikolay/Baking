package com.udacity.zeban.baking;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    @Inject
    public ViewModelFactory() {
        super();
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
//        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
//            return (T) new MainActivityViewModel(movieDBGateway, favoriteMoviesRepository);
//        } else if (modelClass.isAssignableFrom(MovieDetailsViewModel.class)) {
//            return (T) new MovieDetailsViewModel(movieDBGateway, favoriteMoviesRepository);
//        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
