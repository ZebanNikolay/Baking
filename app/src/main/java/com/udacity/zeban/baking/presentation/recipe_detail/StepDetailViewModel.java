package com.udacity.zeban.baking.presentation.recipe_detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.udacity.zeban.baking.data.models.Step;

public class StepDetailViewModel extends ViewModel {

    private MutableLiveData<Step> step;

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public StepDetailViewModel(Step step) {
        this.step = new MutableLiveData<>();
        this.step.setValue(step);
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

}
