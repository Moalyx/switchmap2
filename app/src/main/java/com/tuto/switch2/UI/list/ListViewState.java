package com.tuto.switch2.UI.list;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class ListViewState {

    private final List<ParentViewState> parentViewStates;

    private final List<ChildViewState> childViewStates;

    public ListViewState(List<ParentViewState> parentViewStates, List<ChildViewState> childViewStates) {
        this.parentViewStates = parentViewStates;
        this.childViewStates = childViewStates;
    }

    public List<ParentViewState> getParentViewStates() {
        return parentViewStates;
    }

    public List<ChildViewState> getChildViewStates() {
        return childViewStates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListViewState that = (ListViewState) o;
        return Objects.equals(parentViewStates, that.parentViewStates) && Objects.equals(childViewStates, that.childViewStates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentViewStates, childViewStates);
    }

    @NonNull
    @Override
    public String toString() {
        return "ListViewState{" +
            "parentViewStates=" + parentViewStates +
            ", childViewStates=" + childViewStates +
            '}';
    }
}
