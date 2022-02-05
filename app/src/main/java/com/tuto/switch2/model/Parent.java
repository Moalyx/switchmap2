package com.tuto.switch2.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Parent {

    private final String name;

    public Parent(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parent parent = (Parent) o;
        return name.equals(parent.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @NonNull
    @Override
    public String toString() {
        return "Parent{" +
                "name='" + name + '\'' +
                '}';
    }

}
