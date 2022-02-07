package com.tuto.switch2.UI.main;

import androidx.lifecycle.ViewModel;

import com.tuto.switch2.REPO.ParentRepository;


public class MainViewModel extends ViewModel {

    private final ParentRepository parentRepository;
    private String parentName;
    private String childNames;

    public MainViewModel(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public void onParentNameChanged(String name) {
        parentName = name;
    }

    public void onNameEnfantChanged(String name) {
        childNames = name;
    }

    public void onAddButtonClicked() {
        if (parentName != null && !parentName.isEmpty()) {
            int parentId = parentRepository.onAddParentToList(parentName);

            String[] childNameList = childNames.split("[,; \n]");
            for (String childName : childNameList) {
                parentRepository.onAddChildToList(parentId, childName);
            }
        }
    }
}
