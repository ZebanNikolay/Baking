package com.udacity.zeban.baking.presentation.recipe_detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.databinding.ActivityStepDetailBinding;

public class StepDetailActivity extends AppCompatActivity {

    ActivityStepDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_step_detail);


        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(StepDetailFragment.ARG_STEP,
                    getIntent().getParcelableExtra(StepDetailFragment.ARG_STEP));
            StepDetailFragment fragment = new StepDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_detail_container, fragment)
                    .commit();
        }
    }
}
