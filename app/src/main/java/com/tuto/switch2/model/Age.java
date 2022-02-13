package com.tuto.switch2.model;

import java.util.Objects;

public class Age {

    private final int age;
    private final int id;
    private final int childId;

    public Age(int id, int childId, int age) {
        this.age = age;
        this.id = id;
        this.childId = childId;
    }

    public int getAge() {
        return age;
    }

    public int getAgeId() {
        return id;
    }

    public int getChildId() {
        return childId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Age age1 = (Age) o;
        return age == age1.age && id == age1.id && childId == age1.childId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, id, childId);
    }

    @Override
    public String toString() {
        return "Age{" +
                "age=" + age +
                ", id=" + id +
                ", childId=" + childId +
                '}';
    }
}
