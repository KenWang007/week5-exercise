package com.thoughtworks.javapracise.entity;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KlassTest {
    @Test
    public void should_get_students_after_insert_two_students() throws Exception {
        //Given
        StudentInfo stu1 = new StudentInfo("Tom", "1", 90, 88, 98, 100);
        StudentInfo stu2 = new StudentInfo("Jim", "2", 95, 98, 92, 80);
        Klass klass = new Klass();

        //When
        klass.addStudents(stu1, stu2);
        List<StudentInfo> studentInfos = klass.getAllStudent();

        //Then
        assertEquals(studentInfos.size(), 2);
    }


}
