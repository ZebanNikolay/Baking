package com.udacity.zeban.baking.presentation.recipe_detail;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.udacity.zeban.baking.data.api.RecipesApi;
import com.udacity.zeban.baking.data.models.Step;
import com.udacity.zeban.baking.presentation.recipes_list.RecipesListViewModel;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StepDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory {


    private Step step;

    @Inject
    public StepDetailViewModelFactory(Step step) {
        super();
        this.step = step;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StepDetailViewModel.class)) {
            return (T) new StepDetailViewModel(step);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
