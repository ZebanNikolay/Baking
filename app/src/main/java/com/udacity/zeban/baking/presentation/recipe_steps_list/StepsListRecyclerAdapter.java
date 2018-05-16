package com.udacity.zeban.baking.presentation.recipe_steps_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.data.models.Recipe;
import com.udacity.zeban.baking.data.models.Step;
import com.udacity.zeban.baking.databinding.StepsListContentBinding;

import java.util.List;

public class StepsListRecyclerAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_INGREDIENTS= 0;
    private static final int VIEW_TYPE_STEP = 1;

    private Recipe recipe;
    private List<Step> steps;
    private final OnStepClickListener onClickListener;

    StepsListRecyclerAdapter(Recipe recipe,
                             OnStepClickListener onClickListener) {
        this.recipe = recipe;
        this.steps = recipe.getSteps();
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_TYPE_INGREDIENTS;
        } else {
            return VIEW_TYPE_STEP;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case VIEW_TYPE_INGREDIENTS: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_list_header, parent, false);
                viewHolder = new IngredientsViewHolder(view);
                break;
            }
            case VIEW_TYPE_STEP: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_list_content, parent, false);
                viewHolder = new StepViewHolder(view);
                break;
            }

            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {

            case VIEW_TYPE_INGREDIENTS: {
                IngredientsViewHolder viewHolder = (IngredientsViewHolder) holder;
                viewHolder.bind(recipe.getIngredientsText());
                break;
            }
            case VIEW_TYPE_STEP: {
                StepViewHolder viewHolder = (StepViewHolder) holder;
                viewHolder.bind(steps.get(position - 1));
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid view type, value of " + viewType);
        }
    }

    @Override
    public int getItemCount() {
        return steps.size() + 1;
    }

    class StepViewHolder extends RecyclerView.ViewHolder {

        final StepsListContentBinding binding;

        private StepViewHolder(View view) {
            super(view);
            binding = StepsListContentBinding.bind(view);
        }

        void bind(Step step){
            binding.setStep(step);
            binding.setListener(onClickListener);
        }
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder {

        private TextView ingredientsTextView;

        private IngredientsViewHolder(View view) {
            super(view);
            ingredientsTextView = (TextView) view.findViewById(R.id.ingredients);
        }

        void bind(String ingredients){
            ingredientsTextView.setText(ingredients);
        }
    }

    public interface OnStepClickListener {
        void onClick(Step recipe);
    }
}
