package com.thoughtworks.javapracise.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GradeReportBuilderTest {

    @Mock
    private Klass klass;

    @Test
    public void should_get_report_includes_students() throws Exception {
        //Given
        GradeReportBuilder reportBuilder = new GradeReportBuilder(klass);
        List<StudentInfo> studentInfos = asList(createStudentNamedTom());
        List<StudentGradeItem> gradeItems = asList(new StudentGradeItem("Tom", "1", 90, 88, 98, 100));
        StudentGradeItem studentGradeItemOrigin = gradeItems.get(0);
        when(klass.getAllStudent()).thenReturn(studentInfos);

        //When
        Gradereport report = reportBuilder.buildIndicatedStuReport(studentInfos);

        //Then
        assertEquals(report.getStudentGradeItems().size(), gradeItems.size());
        StudentGradeItem studentGradeItem1 = report.getStudentGradeItems().get(0);
        assertEquals(studentGradeItem1.getName(), studentGradeItemOrigin.getName());
        assertEquals(studentGradeItem1.getNumber(), studentGradeItemOrigin.getNumber());
        assertEquals(studentGradeItem1.getMathsScore(), studentGradeItemOrigin.getMathsScore());
        assertEquals(studentGradeItem1.getChineseScore(), studentGradeItemOrigin.getChineseScore());
        assertEquals(studentGradeItem1.getEnglishScore(), studentGradeItemOrigin.getEnglishScore());
        assertEquals(studentGradeItem1.getProgramScore(), studentGradeItemOrigin.getProgramScore());
    }

    @Test
    public void shoule_get_tital_and_average_score_with_any_stu_when_class_inclues_more_than_one_studentinfos() throws Exception {
        GradeReportBuilder reportBuilder = new GradeReportBuilder(klass);
        List<StudentInfo> studentInfos = asList(
                createStudentNamedTom(),
                createStudentJim()
        );
        when(klass.getAllStudent()).thenReturn(studentInfos);

        //When
        Gradereport report = reportBuilder.buildIndicatedStuReport(studentInfos);

        //Then
        assertThat(report.getStudentGradeItems().get(0).getTotalScore(), is(376));
        assertThat(report.getStudentGradeItems().get(0).getAvergeScore(), is(94));
        assertThat(report.getStudentGradeItems().get(1).getTotalScore(), is(360));
        assertThat(report.getStudentGradeItems().get(1).getAvergeScore(), is(90));
    }

    private StudentInfo createStudentJim() {
        return new StudentInfo("Jim", "2", 95, 93, 92, 80);
    }

    private StudentInfo createStudentNamedTom() {
        return new StudentInfo("Tom", "1", 90, 88, 98, 100);
    }

    @Test
    public void shoule_get_class_total_and_average_scorn_when_class_inclues_more_than_one_studentinfos() throws Exception {
        GradeReportBuilder reportBuilder = new GradeReportBuilder(klass);
        List<StudentInfo> studentInfos = asList(
                createStudentNamedTom(),
                createStudentJim()
        );
        when(klass.getAllStudent()).thenReturn(studentInfos);

        //When
        Gradereport report = reportBuilder.buildIndicatedStuReport(studentInfos);

        //Then
        assertThat(report.getTotalScore(), is(736));
        assertThat(report.getAvergeScore(), is(368));
    }


    @Test
    public void shoule_get_indicated_stus_report_when_include_un_exist_stu_input() throws Exception {
        GradeReportBuilder reportBuilder = new GradeReportBuilder(klass);
        List<StudentInfo> studentInfos = asList(
                createStudentNamedTom(),
                createStudentJim()
        );
        when(klass.getAllStudent()).thenReturn(studentInfos);

        List<StudentInfo> indicatedStudentInfos = asList(
                createStudentNamedTom(),
                createStudentJason()
        );

        //When
        Gradereport report = reportBuilder.buildIndicatedStuReport(indicatedStudentInfos);

        //Then
        assertEquals(report.getStudentGradeItems().size(), 1);
    }

    private StudentInfo createStudentJason() {
        return new StudentInfo("Jason", "3", 95, 93, 92, 80);
    }
}
