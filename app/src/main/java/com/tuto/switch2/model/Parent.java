package com.tuto.switch2.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Parent {

    private final int id;
    private final String name;

    public Parent(int id, String name) {
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
        Parent parent = (Parent) o;
        return id == parent.id && Objects.equals(name, parent.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @NonNull
    @Override
    public String toString() {
        return "Parent{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
