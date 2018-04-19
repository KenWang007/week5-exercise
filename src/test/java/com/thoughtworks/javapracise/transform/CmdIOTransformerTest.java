package com.thoughtworks.javapracise.transform;

import com.thoughtworks.javapracise.entity.Gradereport;
import com.thoughtworks.javapracise.entity.StudentGradeItem;
import com.thoughtworks.javapracise.entity.StudentInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class CmdIOTransformerTest {


    private CmdIOTransformer inputTransformer;

    @Before
    public void setUp() throws Exception {
        inputTransformer = new CmdIOTransformer();
    }

    @Test
    public void should_get_stu_when_input_correct_text() throws Exception {
        //Given
        String studentInfoInput = "Jason, 122, 数学：100, 语文：99, 英语：92, 编程：100";

        //When
        StudentInfo StudentInfo = inputTransformer.formatStudentInfo(studentInfoInput);

        //Then
        assertEquals(StudentInfo.getName(), "Jason");
        assertEquals(StudentInfo.getNumber(), "122");
        assertEquals(StudentInfo.getMathsScore(), 100);
        assertEquals(StudentInfo.getChineseScore(), 99);
        assertEquals(StudentInfo.getEnglishScore(), 92);
        assertEquals(StudentInfo.getProgramScore(), 100);
    }

    @Test
    public void shoud_get_empty_stu_list_when_input_no_match_input() throws Exception {
        //Given
        String StudentInfoInput = "1, any wrong input";

        //When
        List<StudentInfo> stus = inputTransformer.formatStudentNos(StudentInfoInput);

        //Then
        assertEquals(0, stus.size());
    }


    @Test
    public void shoud_get_stu_list_when_input_num_text() throws Exception {
        //Given
        String StudentInfoInput = "122, 12";

        //When
        List<StudentInfo> stus = inputTransformer.formatStudentNos(StudentInfoInput);

        //Then
        assertEquals(stus.size(), 2);
        assertEquals(stus.get(0).getNumber(), "122");
    }

    @Test
    public void shoud_get_deduplicated_stu_list_when_input_repeat_nums_text() throws Exception {
        //Given
        String StudentInfoInput = "122, 12, 122";

        //When
        List<StudentInfo> stus = inputTransformer.formatStudentNos(StudentInfoInput);

        //Then
        assertEquals(stus.size(), 2);
        assertEquals(stus.get(0).getNumber(), "122");
    }


    @Test
    public void shoud_get_report_format_when_input_correct_report() throws Exception {
        //Given
        String reportTextOrigin = "成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分 \n" +
                "========================\n" +
                "张三|75|95|80|82|83|332\n" +
                "李四|85|80|70|81|79|316\n" +
                "========================\n" +
                "全班总分：648\n" +
                "全班总平均分：324";

        Gradereport report = new Gradereport();
        report.setStudentGradeItems(asList(
                new StudentGradeItem("张三", "111", 75, 95, 80, 82),
                new StudentGradeItem("李四", "222", 85, 80, 70, 81)));

        //When
        String reportText = inputTransformer.formatReportText(report);

        //Then
        assertEquals(reportText, reportTextOrigin);
    }
}