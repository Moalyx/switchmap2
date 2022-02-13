package com.tuto.switch2.UI.list;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ChildViewState {

    private final int id;
    private final String name;


    public ChildViewState(int id, String name) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildViewState that = (ChildViewState) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @NonNull
    @Override
    public String toString() {
        return "ChildViewState{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
