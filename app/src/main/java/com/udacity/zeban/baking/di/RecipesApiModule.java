package com.udacity.zeban.baking.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.udacity.zeban.baking.data.api.RecipesApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = OkHttpClientModule.class)
public class RecipesApiModule {

    private static final String RECIPES_HOST = "https://d17h27t6h515a5.cloudfront.net/";

    @Singleton
    @Provides
    public RecipesApi provideRecipesApiModule(Retrofit retrofit){
        return retrofit.create(RecipesApi.class);
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient,
                             GsonConverterFactory gsonConverterFactory, RxJava2CallAdapterFactory rxJava2CallFactory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(RECIPES_HOST)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallFactory)
                .build();
    }

    @Singleton
    @Provides
    public Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder
                .create();
    }

    @Singleton
    @Provides
    public GsonConverterFactory provideGsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    public RxJava2CallAdapterFactory providesRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }
}
