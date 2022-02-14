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

    public void onAgeEnfantChanged(String age){
        ages = age;
    }

    public void onAddButtonClicked() {
        if (parentName != null && !parentName.isEmpty()) {
            int parentId = parentRepository.onAddParentToList(parentName);
            //int childId = parentRepository.onAddChildToList(parentId, childNames); ICi aussi probleme car cela rajouter une ligne dans la recyclerview car j'appelé deux fois cette methode l34 et l47
            int childId = parentRepository.childId();

            String[] childNameList = childNames.split("[,; \n]");
            for (String childName : childNameList) {
                childName = childName.trim();
                parentRepository.onAddChildToList(parentId, childName);
            }

            String[] ageChildList = ages.split("[,; \n]");
            for (String ageChild : ageChildList){
                ageChild = ageChild.trim();
                childId++; //le probleme etait ici, je n'avais pas incrémenter l'id du coup tous les ages avaient le meme childId
                parentRepository.onAddAgeToList(childId, Integer.parseInt(ageChild));
            }
        }
    }
}
