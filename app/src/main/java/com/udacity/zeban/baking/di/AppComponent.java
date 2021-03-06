package com.udacity.zeban.baking.di;

import com.udacity.zeban.baking.presentation.recipe_steps_list.StepsListActivity;
import com.udacity.zeban.baking.presentation.recipes_list.RecipesListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        ContextModule.class,
        OkHttpClientModule.class,
        RecipesApiModule.class
})
@Singleton
public interface AppComponent {

    void inject(RecipesListActivity recipesListActivity);
    void inject(StepsListActivity stepsListActivity);

}