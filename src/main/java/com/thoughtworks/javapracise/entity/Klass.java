package com.thoughtworks.javapracise.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Klass {
    private List<StudentInfo> studentInfos;

    public Klass() {
        studentInfos = new ArrayList<>();
    }

    public void addStudents(StudentInfo... stu) {
        this.studentInfos.addAll(asList(stu));
    }

    public List<StudentInfo> getAllStudent() {
        return this.studentInfos.stream().collect(Collectors.toList());
    }
}
