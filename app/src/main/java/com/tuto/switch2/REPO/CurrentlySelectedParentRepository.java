package com.tuto.switch2.REPO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class CurrentlySelectedParentRepository {

    private final MutableLiveData<Integer> selectedParentIdMutableLiveData = new MutableLiveData<>();

    public void setCurrentlySelectedParentId(int id) {
        selectedParentIdMutableLiveData.setValue(id);
    }

    public LiveData<Integer> getSelectedParentIdLiveData() {
        return selectedParentIdMutableLiveData;
    }
}
