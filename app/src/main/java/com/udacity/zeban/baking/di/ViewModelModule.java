package com.udacity.zeban.baking.di;

import android.arch.lifecycle.ViewModelProvider;

import com.udacity.zeban.baking.ViewModelFactory;
import com.udacity.zeban.baking.data.api.RecipesApi;

import dagger.Binds;
import dagger.Module;

@Module(includes = {RecipesApiModule.class})
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.NewInstanceFactory bindVMFactory(ViewModelFactory viewModelFactory);

}
