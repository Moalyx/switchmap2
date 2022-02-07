package com.tuto.switch2.UI.list;

import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.tuto.switch2.REPO.CurrentlySelectedParentRepository;
import com.tuto.switch2.REPO.ParentRepository;
import com.tuto.switch2.model.Child;
import com.tuto.switch2.model.Parent;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    private final CurrentlySelectedParentRepository currentlySelectedParentRepository;
    private final ParentRepository parentRepository;

    private final MediatorLiveData<ListViewState> mediatorLiveData = new MediatorLiveData<>();

    public ListViewModel(
        CurrentlySelectedParentRepository currentlySelectedParentRepository,
        ParentRepository parentRepository
    ) {
        this.currentlySelectedParentRepository = currentlySelectedParentRepository;
        this.parentRepository = parentRepository;

        LiveData<List<Parent>> parentsLiveData = parentRepository.getParentListMutableLiveData();
        LiveData<Integer> currentlySelectedParentIdLiveData = currentlySelectedParentRepository.getSelectedParentIdLiveData();
        LiveData<List<Child>> childForSelectedParentLiveData = Transformations.switchMap(currentlySelectedParentRepository.getSelectedParentIdLiveData(), new Function<Integer, LiveData<List<Child>>>() {
            @Override
            public LiveData<List<Child>> apply(Integer newParentId) {
                return parentRepository.getAllChildrenByParentId(newParentId);
            }
        });

        mediatorLiveData.addSource(parentsLiveData, new Observer<List<Parent>>() {
            @Override
            public void onChanged(List<Parent> parents) {
                combine(parents, currentlySelectedParentIdLiveData.getValue(), childForSelectedParentLiveData.getValue());
            }
        });

        mediatorLiveData.addSource(currentlySelectedParentIdLiveData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentlySelectedParentId) {
                combine(parentsLiveData.getValue(), currentlySelectedParentId, childForSelectedParentLiveData.getValue());
            }
        });

        mediatorLiveData.addSource(childForSelectedParentLiveData, new Observer<List<Child>>() {
            @Override
            public void onChanged(List<Child> children) {
                combine(parentsLiveData.getValue(), currentlySelectedParentIdLiveData.getValue(), children);
            }
        });
    }

    private void combine(@Nullable List<Parent> parents, @Nullable Integer currentlySelectedParentId, @Nullable List<Child> children) {
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

        mediatorLiveData.setValue(
            new ListViewState(
                parentViewStates,
                childViewStates
            )
        );
    }

    public LiveData<ListViewState> getViewStateLiveData() {
        return mediatorLiveData;
    }

    public void onParentClicked(int parentName) {
        currentlySelectedParentRepository.setCurrentlySelectedParentId(parentName);
    }

}
