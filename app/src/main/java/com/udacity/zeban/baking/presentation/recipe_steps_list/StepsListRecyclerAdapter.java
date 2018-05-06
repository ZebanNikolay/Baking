package com.udacity.zeban.baking.presentation.recipe_steps_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.zeban.baking.R;
import com.udacity.zeban.baking.data.models.Step;
import com.udacity.zeban.baking.databinding.RecipesListContentBinding;
import com.udacity.zeban.baking.databinding.StepsListContentBinding;

import java.util.List;

public class StepsListRecyclerAdapter
        extends RecyclerView.Adapter<StepsListRecyclerAdapter.ViewHolder> {

    private List<Step> steps;
    private final OnStepClickListener onClickListener;

    StepsListRecyclerAdapter(List<Step> steps,
                             OnStepClickListener onClickListener) {
        this.steps = steps;
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.steps_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(steps.get(position));
    }

    public void swapData(List<Step> steps) {
        this.steps = steps;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return steps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final StepsListContentBinding binding;

        private
        ViewHolder(View view) {
            super(view);
            binding = StepsListContentBinding.bind(view);
        }

        void bind(Step recipe){
            binding.setStep(recipe);
            binding.setListener(onClickListener);
        }
    }

    public interface OnStepClickListener {
        void onClick(Step recipe);
    }
}
