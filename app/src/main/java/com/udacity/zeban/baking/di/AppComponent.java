package com.udacity.zeban.baking.di;

import com.udacity.zeban.baking.App;
import com.udacity.zeban.baking.data.api.RecipesApi;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AppModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class,
        FragmentModule.class})
public interface AppComponent {
    void inject(App app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(App app);

        AppComponent build();
    }
}
