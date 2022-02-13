package com.tuto.switch2.REPO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CurrentlySelectedChildRepository {

    private final MutableLiveData<Integer> selectedChildIdMutableLiveData = new MutableLiveData<>();

    public void setCurrentlySelectedchlidId(int id) {
        selectedChildIdMutableLiveData.setValue(id);
    }

    public LiveData<Integer> getSelectedChildIdLiveData() {
        return selectedChildIdMutableLiveData;
    }

}
