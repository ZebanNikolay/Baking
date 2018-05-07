package com.udacity.zeban.baking.presentation.recipe_detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.data.models.Step;

public class StepDetailFragment extends Fragment {

    public static final String ARG_STEP = "step_key";

    private Step step;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_STEP)) {
            step = getArguments().getParcelable(ARG_STEP);
        } else {
            throw new IllegalStateException("step == null");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_detail, container, false);

        return rootView;
    }
}
