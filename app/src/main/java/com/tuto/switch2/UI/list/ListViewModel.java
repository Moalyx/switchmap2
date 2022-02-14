package com.tuto.switch2.UI.list;

import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.tuto.switch2.REPO.CurrentlySelectedChildRepository;
import com.tuto.switch2.REPO.CurrentlySelectedParentRepository;
import com.tuto.switch2.REPO.ParentRepository;
import com.tuto.switch2.model.Age;
import com.tuto.switch2.model.Child;
import com.tuto.switch2.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    private final CurrentlySelectedParentRepository currentlySelectedParentRepository;
    private final CurrentlySelectedChildRepository currentlySelectedChildRepository;

    private final MediatorLiveData<ListViewState> mediatorLiveData = new MediatorLiveData<>();

    public ListViewModel(
        CurrentlySelectedParentRepository currentlySelectedParentRepository,
        CurrentlySelectedChildRepository currentlySelectedChildRepository,
        ParentRepository parentRepository
    ) {
        this.currentlySelectedParentRepository = currentlySelectedParentRepository;
        this.currentlySelectedChildRepository = currentlySelectedChildRepository;

        LiveData<List<Parent>> parentsLiveData = parentRepository.getParentListMutableLiveData();
        LiveData<Integer> currentlySelectedParentIdLiveData = currentlySelectedParentRepository.getSelectedParentIdLiveData();
        LiveData<List<Child>> childForSelectedParentLiveData = Transformations.switchMap(currentlySelectedParentRepository.getSelectedParentIdLiveData(), new Function<Integer, LiveData<List<Child>>>() {
            @Override
            public LiveData<List<Child>> apply(Integer newParentId) {
                return parentRepository.getAllChildrenByParentId(newParentId);
            }
        });
        LiveData<List<Age>> ageForSelectedChildLiveData = Transformations.switchMap(currentlySelectedChildRepository.getSelectedChildIdLiveData(), new Function<Integer, LiveData<List<Age>>>() {
            @Override
            public LiveData<List<Age>> apply(Integer newChildId) {
                return parentRepository.getAllAgeByChildrenId(newChildId);
            }
        });

        mediatorLiveData.addSource(parentsLiveData, new Observer<List<Parent>>() {
            @Override
            public void onChanged(List<Parent> parents) {
                combine(parents, currentlySelectedParentIdLiveData.getValue(), childForSelectedParentLiveData.getValue(), ageForSelectedChildLiveData.getValue());
            }
        });

        mediatorLiveData.addSource(currentlySelectedParentIdLiveData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentlySelectedParentId) {
                combine(parentsLiveData.getValue(), currentlySelectedParentId, childForSelectedParentLiveData.getValue(), ageForSelectedChildLiveData.getValue());
            }
        });

        mediatorLiveData.addSource(childForSelectedParentLiveData, new Observer<List<Child>>() {
            @Override
            public void onChanged(List<Child> children) {
                combine(parentsLiveData.getValue(), currentlySelectedParentIdLiveData.getValue(), children, ageForSelectedChildLiveData.getValue());
            }
        });

        mediatorLiveData.addSource(ageForSelectedChildLiveData, new Observer<List<Age>>() {
            @Override
            public void onChanged(List<Age> ages) {
                combine(parentsLiveData.getValue(), currentlySelectedParentIdLiveData.getValue(), childForSelectedParentLiveData.getValue(), ages);
            }
        });
    }

    private void combine(
        @Nullable List<Parent> parents,
        @Nullable Integer currentlySelectedParentId,
        @Nullable List<Child> children,
        @Nullable List<Age> ages
    ) {
        if (parents == null) {
            return;
        }

        List<ParentViewState> parentViewStates = new ArrayList<>();

        for (Parent parent : parents) {
            parentViewStates.add(new ParentViewState(
                parent.getId(),
                parent.getName(),
                currentlySelectedParentId != null && currentlySelectedParentId == parent.getId()
            ));
        }

        List<ChildViewState> childViewStates = new ArrayList<>();

        if (children != null) {
            for (Child child : children) {
                childViewStates.add(new ChildViewState(
                    child.getId(),
                    child.getName()
                ));
            }
        }

        List<AgeViewState> ageViewStates = new ArrayList<>();

        if(ages != null){
            for (Age age : ages){
                ageViewStates.add(new AgeViewState(
                        ""+ age.getAge(),
                        age.getAgeId()
                ));
            }
        }

        mediatorLiveData.setValue(
            new ListViewState(
                parentViewStates,
                childViewStates,
                ageViewStates
            )
        );
    }

    public LiveData<ListViewState> getListViewStateLiveData() {
        return mediatorLiveData;
    }

    public void onParentClicked(int parentId) {
        currentlySelectedParentRepository.setCurrentlySelectedParentId(parentId);
    }

    public void onChildClicked(int childId){
        currentlySelectedChildRepository.setCurrentlySelectedChildId(childId);
    }

}
