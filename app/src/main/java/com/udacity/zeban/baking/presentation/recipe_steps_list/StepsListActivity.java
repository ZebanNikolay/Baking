package com.udacity.zeban.baking.presentation.recipe_steps_list;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.data.models.Recipe;
import com.udacity.zeban.baking.data.models.Step;
import com.udacity.zeban.baking.databinding.ActivityStepsListBinding;
import com.udacity.zeban.baking.presentation.recipe_detail.StepDetailActivity;
import com.udacity.zeban.baking.presentation.recipe_detail.StepDetailFragment;

import java.util.List;

public class StepsListActivity extends AppCompatActivity {

    public static final String ARG_RECIPE = "recipe_key";

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

        if (getResources().getBoolean(R.bool.isTablet)) {
            initStepDetailFragment(recipe.getSteps().get(0));
        }

        setupRecyclerView((RecyclerView) binding.frameLayout.findViewById(R.id.steps_list), recipe);

        Toolbar toolbar = (Toolbar) binding.toolbarLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, @NonNull Recipe recipe) {
        adapter = new StepsListRecyclerAdapter(recipe, step -> {
            if (getResources().getBoolean(R.bool.isTablet)) {
                initStepDetailFragment(step);
            } else {
                Context context = StepsListActivity.this;
                Intent intent = new Intent(context, StepDetailActivity.class);
                intent.putExtra(StepDetailFragment.ARG_STEP, step);

                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void initStepDetailFragment(Step step) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(StepDetailFragment.ARG_STEP, step);
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(arguments);
        this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.step_detail_container, fragment)
                .commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
