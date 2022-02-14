package com.tuto.switch2.REPO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tuto.switch2.model.Age;
import com.tuto.switch2.model.Child;
import com.tuto.switch2.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class ParentRepository {

    private final MutableLiveData<List<Parent>> parentListMutableLiveData = new MutableLiveData<>();
    private final List<Parent> parents = new ArrayList<>();
    private final List<Child> children = new ArrayList<>();
    private final List<Age> ageList = new ArrayList<>();
    private int currentMaxParentId = 0;
    private int currentMaxChildId = 0;
    private int currentMaxAgeId = 0;

    public LiveData<List<Parent>> getParentListMutableLiveData() {
        return parentListMutableLiveData;
    }

    public LiveData<List<Child>> getAllChildrenByParentId(int id) {
        MutableLiveData<List<Child>> childListMutableLiveData = new MutableLiveData<>();

        List<Child> result = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            Child child = children.get(i);

            if (child.getParentId() == id) {
                result.add(children.get(i));
            }
        }

        childListMutableLiveData.setValue(result);
        return childListMutableLiveData;
    }

    public LiveData<List<Age>> getAllAgeByChildrenId(int id) {
        MutableLiveData<List<Age>> ageMutableLiveData = new MutableLiveData<>();

        List<Age> result = new ArrayList<>();
        for (int i = 0; i < ageList.size(); i++) {
            Age age = ageList.get(i);
            if (age.getChildId() == id) {
                result.add(ageList.get(i));
            }
        }
        ageMutableLiveData.setValue(result);
        return ageMutableLiveData;
    }

    public int onAddParentToList(String name) {
        currentMaxParentId++;

        parents.add(new Parent(currentMaxParentId, name));
        parentListMutableLiveData.setValue(parents);

        return currentMaxParentId;
    }

    public int childId(){
        int id =0;
        if (!children.isEmpty()){
            for(int i = 0; i < children.size(); i++){
                 id = children.get(i).getId();
            }
        }
        return id;
    }

    public void onAddChildToList(int parentId, String name) {
        currentMaxChildId++;
        children.add(new Child(currentMaxChildId, parentId, name));

//        Child found = null;
//
//        for (Child child : children) {
//            if (child.getName().equals(name)) {
//                found = child;
//                break;
//            }
//        }
//
//        if (found == null) {
//            currentMaxChildId++;
//
//            children.add(new Child(currentMaxChildId, parentId, name));
//        } else {
//            children.add(new Child(found.getId(), parentId, name));
//        }

    }

    public void onAddAgeToList(int childId, int age) {
        currentMaxAgeId++;
        ageList.add(new Age(currentMaxAgeId, childId, age));
    }
}