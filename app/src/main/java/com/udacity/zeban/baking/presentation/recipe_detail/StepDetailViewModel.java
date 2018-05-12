package com.udacity.zeban.baking.presentation.recipe_detail;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableLong;

import com.udacity.zeban.baking.data.models.Step;

public class StepDetailViewModel extends ViewModel {

    public ObservableField<Step> step = new ObservableField<>();

    public ObservableLong playerPosition = new ObservableLong();

    public ObservableBoolean loading = new ObservableBoolean();

    public StepDetailViewModel(Step step) {
        this.step.set(step);
    }

}
