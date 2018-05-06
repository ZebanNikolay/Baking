package com.udacity.zeban.baking.presentation.recipe_detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.data.models.Recipe;

public class RecipesDetailFragment extends Fragment {

    public static final String ARG_STEP = "step_key";

    private Recipe recipe;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_STEP)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            recipe = getArguments().getParcelable(ARG_STEP);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipes_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (recipe != null) {
            ((TextView) rootView.findViewById(R.id.recipes_detail)).setText(recipe.getName());
        }

        return rootView;
    }
}
