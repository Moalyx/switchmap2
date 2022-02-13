package com.tuto.switch2.UI.list;

import java.util.Objects;

public class AgeViewState {

    private final String age;
    private final int id;


    public AgeViewState(String age, int id) {
        this.age = age;
        this.id = id;

    }

    public String getAge() {
        return age;
    }

    public int getAgeId() {
        return id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgeViewState that = (AgeViewState) o;
        return age == that.age && id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, id);
    }

    @Override
    public String toString() {
        return "AgeViewState{" +
                "age=" + age +
                ", id=" + id +
                '}';
    }
}
