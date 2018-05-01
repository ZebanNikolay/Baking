package com.udacity.zeban.baking.di;

import android.content.Context;

import com.udacity.zeban.baking.App;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

@Module(includes = {AndroidInjectionModule.class})
public class AppModule {

    @Provides
    public Context provideContext(App app) {
        return app.getApplicationContext();
    }

}
