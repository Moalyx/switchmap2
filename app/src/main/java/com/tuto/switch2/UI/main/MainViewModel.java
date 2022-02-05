package com.tuto.switch2.UI.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tuto.switch2.REPO.TestRepository;
import com.tuto.switch2.model.Enfant;
import com.tuto.switch2.model.Parent;


public class MainViewModel extends ViewModel {

    private final TestRepository testRepository;
    private final MutableLiveData<String> parentNameMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> enfantNameMutableLiveData = new MutableLiveData<>();

    public MainViewModel(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void onParentNameChanged(String name) {
        parentNameMutableLiveData.setValue(name);
    }

    public void onNameEnfantChanged(String name) {
        enfantNameMutableLiveData.setValue(name);
    }

    public void addParentToList(String name) {
        testRepository.onAddParentToList(new Parent(name));
    }

    public void onAddEnfantToList(@NonNull String parentName, @NonNull String name) {
        String[] enfantList;
        enfantList = name.split("[,; \n]");
        for (String s : enfantList) {
            name = s;
            testRepository.onAddEnfanttoList(new Enfant(parentName, name));
        }
    }

    public void onAddButtonClicked() {
        String parname = parentNameMutableLiveData.getValue();
        String enfname = enfantNameMutableLiveData.getValue();
        if (parname != null && enfname != null) {
            addParentToList(parname);
            onAddEnfantToList(parname, enfname);
        }
    }

}
