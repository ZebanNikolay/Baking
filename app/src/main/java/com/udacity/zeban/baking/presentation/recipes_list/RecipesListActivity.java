package com.udacity.zeban.baking.presentation.recipes_list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.udacity.zeban.baking.App;
import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.ViewModelFactory;
import com.udacity.zeban.baking.databinding.ActivityRecipesListBinding;
import com.udacity.zeban.baking.presentation.recipe_detail.RecipeDetailActivity;
import com.udacity.zeban.baking.presentation.recipe_detail.RecipesDetailFragment;

import java.util.ArrayList;

import javax.inject.Inject;

public class RecipesListActivity extends AppCompatActivity {

    private boolean twoPane;

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

        setupRecyclerView((RecyclerView) binding.frameLayout.findViewById(R.id.recipes_list));

        viewModel.getMovies().observe(this, adapter::swapData);

        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(getTitle());

        if (binding.frameLayout.findViewById(R.id.recipes_detail_container) != null) {
            twoPane = true;
        }


    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        adapter = new RecipesListRecyclerAdapter(new ArrayList<>(), recipe -> {
            if (twoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(RecipesDetailFragment.ARG_RECIPE, recipe);
                RecipesDetailFragment fragment = new RecipesDetailFragment();
                fragment.setArguments(arguments);
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipes_detail_container, fragment)
                        .commit();
            } else {
                Context context = RecipesListActivity.this;
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra(RecipesDetailFragment.ARG_RECIPE, recipe);

                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
