package com.tuto.switch2.model;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Enfant {

    private final String parentName;
    private final String name;

    public Enfant(String parentName, String name) {
        this.parentName = parentName;
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enfant enfant = (Enfant) o;
        return parentName.equals(enfant.parentName) && name.equals(enfant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentName, name);
    }

    @NonNull
    @Override
    public String toString() {
        return "Enfant{" +
                "parentId=" + parentName +
                ", name='" + name + '\'' +
                '}';
    }
}
