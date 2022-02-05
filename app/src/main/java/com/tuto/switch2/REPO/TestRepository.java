package com.tuto.switch2.REPO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.tuto.switch2.model.Enfant;
import com.tuto.switch2.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class TestRepository {

    private final MutableLiveData<String> selectedParentNameMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Enfant>> enfantListMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Parent>> parentListMutableLiveData = new MutableLiveData<>();
    private final List<Enfant> enfants = new ArrayList<>();
    private final List<Parent> parents = new ArrayList<>();

    public LiveData<List<Parent>> getParentListMutableLiveData() {
        return parentListMutableLiveData;
    }

    public List<Parent> getparentslist() {
        List<Parent> parentlist;
        parentlist = parentListMutableLiveData.getValue();
        return parentlist;
    }

    public LiveData<List<Enfant>> getAllEnfantsByParentsName(String parentName) {
        enfantListMutableLiveData.setValue(enfants);                                // ici difficulté j'ai du passer par le debugger pour voir que ma liste enfants avaient tous les enfants
        List<Enfant> enfants1 = enfantListMutableLiveData.getValue();               //alors que sans la ligne 36 je ne pouvais pas recup des enfants, j'avais mis get value mais cela ne
        List<Enfant> enfants2 = new ArrayList<>();
        if (enfants1 != null) {                                                      //fonctionnait pas
            for (int i = 0; i < enfants1.size(); i++) {
                if (parentName.equals(enfants1.get(i).getParentName())) {
                    enfants2.add(enfants1.get(i));
                }
            }
        }
        enfantListMutableLiveData.setValue(enfants2);
        return enfantListMutableLiveData;
    }

    public LiveData<List<Enfant>> getAllEnfantsBySelectedParentName() {
        return Transformations.switchMap(selectedParentNameMutableLiveData, this::getAllEnfantsByParentsName);
    }

    public void onParentNameChanged(String parentName) {
        selectedParentNameMutableLiveData.setValue(parentName);
    }

    public List<Enfant> getenfantslist() {
        List<Enfant> enfantlist;
        enfantlist = enfantListMutableLiveData.getValue();
        return enfantlist;
    }

    public void onAddParentToList(Parent parent) {
        parents.add(parent);
        parentListMutableLiveData.setValue(parents);
    }

    public void onAddEnfanttoList(Enfant enfant) {
        enfants.add(enfant);
        enfantListMutableLiveData.setValue(enfants);
    }

}