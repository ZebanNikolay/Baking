package com.udacity.zeban.baking.data.api;

import com.udacity.zeban.baking.data.models.Recipe;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RecipesApi {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> getRecipes();
}
