package com.tuto.switch2.UI.main;

import androidx.lifecycle.ViewModel;

import com.tuto.switch2.REPO.ParentRepository;


public class MainViewModel extends ViewModel {

    private final ParentRepository parentRepository;
    private String parentName;
    private String childNames;
    private String ages;

    public MainViewModel(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public void onParentNameChanged(String name) {
        parentName = name;
    }

    public void onNameEnfantChanged(String name) {
        childNames = name;
    }

    public void onAgeEnfantChanged(String age) {
        ages = age;
    }

    public void onAddButtonClicked() {
        if (parentName != null && !parentName.isEmpty()) {
            int parentId = parentRepository.onAddParentToList(parentName);

            String[] childNameList = childNames.split("[,; \n]");
            String[] ageChildList = ages.split("[,; \n]");

            for (int i = 0; i < childNameList.length; i++) {
                String childName = childNameList[i].trim();
                String childAge = ageChildList[i].trim();
                int childId = parentRepository.onAddChildToList(parentId, childName);
                parentRepository.onAddAgeToList(childId, Integer.parseInt(childAge));
            }
        }
    }
}
