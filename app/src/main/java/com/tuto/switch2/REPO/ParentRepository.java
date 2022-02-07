package com.tuto.switch2.REPO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tuto.switch2.model.Child;
import com.tuto.switch2.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class ParentRepository {

    private final MutableLiveData<List<Parent>> parentListMutableLiveData = new MutableLiveData<>();
    private final List<Child> children = new ArrayList<>();
    private int currentMaxParentId = 0;
    private final List<Parent> parents = new ArrayList<>();
    private int currentMaxChildId = 0;

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

    public int onAddParentToList(String name) {
        currentMaxParentId++;

        parents.add(new Parent(currentMaxParentId, name));
        parentListMutableLiveData.setValue(parents);

        return currentMaxParentId;
    }

    public void onAddChildToList(int parentId, String name) {
        Child found = null;

        for (Child child : children) {
            if (child.getName().equals(name)) {
                found = child;
                break;
            }
        }

        if (found == null) {
            currentMaxChildId++;

            children.add(new Child(currentMaxChildId, parentId, name));
        } else {
            children.add(new Child(found.getId(), parentId, name));
        }
    }
}