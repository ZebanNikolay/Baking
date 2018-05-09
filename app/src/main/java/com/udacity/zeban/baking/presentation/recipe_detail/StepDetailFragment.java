package com.udacity.zeban.baking.presentation.recipe_detail;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.data.models.Step;
import com.udacity.zeban.baking.databinding.FragmentStepDetailBinding;
import com.udacity.zeban.baking.presentation.recipes_list.RecipesListViewModel;

public class StepDetailFragment extends Fragment {

    public static final String ARG_STEP = "step_key";

    private Step step;

    FragmentStepDetailBinding binding;

    private StepDetailViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_step_detail, container, false);

        if (getArguments().containsKey(ARG_STEP)) {
            step = getArguments().getParcelable(ARG_STEP);
        } else {
            throw new IllegalStateException("step == null");
        }
        StepDetailViewModelFactory factory = new StepDetailViewModelFactory(step);
        viewModel = ViewModelProviders.of(this, factory).get(StepDetailViewModel.class);

        binding.setVm(viewModel);
        return binding.getRoot();
    }
}
