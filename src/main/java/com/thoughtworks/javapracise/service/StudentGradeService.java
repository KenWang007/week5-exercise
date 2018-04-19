package com.thoughtworks.javapracise.service;

import com.thoughtworks.javapracise.entity.GradeReportBuilder;
import com.thoughtworks.javapracise.entity.Gradereport;
import com.thoughtworks.javapracise.entity.Klass;
import com.thoughtworks.javapracise.entity.StudentInfo;

import java.util.List;

public class StudentGradeService {

    private Klass klass;
    private GradeReportBuilder gradeReportBuilder;

    public StudentGradeService(Klass klass, GradeReportBuilder gradeReportBuilder) {

        this.klass = klass;
        this.gradeReportBuilder = gradeReportBuilder;
    }

    public void addStudent(StudentInfo stu) {
        this.klass.addStudents(stu);
    }

    public Gradereport generateReport(List<StudentInfo> stuList) {
        return this.gradeReportBuilder.buildIndicatedStuReport(stuList);
    }
}
