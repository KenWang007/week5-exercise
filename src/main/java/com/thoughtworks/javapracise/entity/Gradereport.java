package com.thoughtworks.javapracise.entity;

import java.util.List;

public class Gradereport {

    public Gradereport() {
    }

    private List<StudentGradeItem> studentGradeItems;


    public List<StudentGradeItem> getStudentGradeItems() {
        return this.studentGradeItems;
    }

    public void setStudentGradeItems(List<StudentGradeItem> studentGradeItem) {
        this.studentGradeItems = studentGradeItem;
    }

    public int getTotalScore() {
        return this.studentGradeItems.stream().mapToInt(item -> item.getTotalScore()).sum();
    }

    public int getAvergeScore() {
        return getTotalScore() / this.studentGradeItems.size();
    }
}
