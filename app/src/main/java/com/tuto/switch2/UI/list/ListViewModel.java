package com.tuto.switch2.UI.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.tuto.switch2.REPO.TestRepository;
import com.tuto.switch2.model.Enfant;
import com.tuto.switch2.model.Parent;

import java.util.List;

public class ListViewModel extends ViewModel {

    private final TestRepository testRepository;


    public ListViewModel(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public LiveData<List<Parent>> getParentList() {
        return testRepository.getParentListMutableLiveData();
    }

    public List<Parent> getparentslist() {
        return testRepository.getparentslist();
    }

    public List<Enfant> getenfantslist() {
        return testRepository.getenfantslist();
    }

    public LiveData<List<Enfant>> getAllEnfantsBySelectedParentName() {
        return testRepository.getAllEnfantsBySelectedParentName();
    }

    public void onParentNameChanged(String parentName) {
        testRepository.onParentNameChanged(parentName);
    }

}
