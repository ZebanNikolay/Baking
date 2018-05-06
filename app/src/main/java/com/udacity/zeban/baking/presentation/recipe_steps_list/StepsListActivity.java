package com.udacity.zeban.baking.presentation.recipe_steps_list;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.data.models.Recipe;
import com.udacity.zeban.baking.data.models.Step;
import com.udacity.zeban.baking.databinding.ActivityRecipesListBinding;
import com.udacity.zeban.baking.databinding.ActivityStepsListBinding;
import com.udacity.zeban.baking.presentation.recipe_detail.RecipeDetailActivity;
import com.udacity.zeban.baking.presentation.recipe_detail.RecipesDetailFragment;

import java.util.List;

public class StepsListActivity extends AppCompatActivity {

    public static final String ARG_RECIPE = "recipe_key";

    private boolean twoPane;

    private ActivityStepsListBinding binding;

    private StepsListRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_steps_list);

        if (getIntent().getParcelableExtra(ARG_RECIPE) == null) {
            throw new IllegalArgumentException("recipe == null");
        }

        Recipe recipe = (Recipe) getIntent().getParcelableExtra(ARG_RECIPE);


        if (binding.frameLayout.findViewById(R.id.recipes_detail_container) != null) {
            twoPane = true;
        }

        setupRecyclerView((RecyclerView) binding.frameLayout.findViewById(R.id.recipes_list), recipe.getSteps());


    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, @NonNull List<Step> steps) {
        adapter = new StepsListRecyclerAdapter(steps, step -> {
            if (twoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelable(RecipesDetailFragment.ARG_STEP, step);
                RecipesDetailFragment fragment = new RecipesDetailFragment();
                fragment.setArguments(arguments);
                this.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.recipes_detail_container, fragment)
                        .commit();
            } else {
                Context context = StepsListActivity.this;
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra(RecipesDetailFragment.ARG_STEP, step);

                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
