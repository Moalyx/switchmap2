package com.tuto.switch2.UI.list;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ParentViewState {

    private final int id;
    private final String name;
    private final boolean isSelected;

    public ParentViewState(int id, String name, boolean isSelected) {
        this.id = id;
        this.name = name;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentViewState that = (ParentViewState) o;
        return id == that.id && isSelected == that.isSelected && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isSelected);
    }

    @NonNull
    @Override
    public String toString() {
        return "ParentViewState{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", isSelected=" + isSelected +
            '}';
    }
}
