package com.udacity.zeban.baking.presentation.recipes_list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.udacity.zeban.baking.App;
import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.databinding.ActivityRecipesListBinding;
import com.udacity.zeban.baking.presentation.recipe_steps_list.StepsListActivity;

import java.util.ArrayList;

import javax.inject.Inject;

public class RecipesListActivity extends AppCompatActivity {

    private ActivityRecipesListBinding binding;

    private RecipesListViewModel viewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    private RecipesListRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipes_list);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipesListViewModel.class);

        setupRecyclerView(binding.recyclerView);

        viewModel.getRecipes().observe(this, adapter::swapData);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter = new RecipesListRecyclerAdapter(new ArrayList<>(), recipe -> {
            Context context = RecipesListActivity.this;
            Intent intent = new Intent(context, StepsListActivity.class);
            intent.putExtra(StepsListActivity.ARG_RECIPE, recipe);

            context.startActivity(intent);
        });
        if (getResources().getBoolean(R.bool.isTablet)) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }
        recyclerView.setAdapter(adapter);
    }

}
