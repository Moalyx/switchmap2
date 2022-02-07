package com.tuto.switch2.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Child {

    private final int id;
    private final int parentId;
    private final String name;

    public Child(int id, int parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return id == child.id && parentId == child.parentId && Objects.equals(name, child.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name);
    }

    @NonNull
    @Override
    public String toString() {
        return "Child{" +
            "id=" + id +
            ", parentId='" + parentId + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
