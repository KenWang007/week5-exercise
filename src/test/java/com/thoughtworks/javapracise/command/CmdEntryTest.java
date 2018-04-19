package com.thoughtworks.javapracise.command;

import com.thoughtworks.javapracise.entity.*;
import com.thoughtworks.javapracise.service.StudentGradeService;
import com.thoughtworks.javapracise.transform.CmdIOTransformer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CmdEntryTest {

    private CmdEntry commandHandler;
    @Mock
    private StudentGradeService mockStudentGradeService;
    @Mock
    private CmdIOTransformer inputTransformer;

    @Before
    public void setUp() throws Exception {
        commandHandler = new CmdEntry(mockStudentGradeService, inputTransformer);
    }

    @Test
    public void should_return_add_student_print_output_when_input_1() throws Exception {
        //Given
        //When
        commandHandler.execute("-1");
        CmdParam param = commandHandler.execute("1");
        //Then
        assertThat(param.getOutput(), is("请输入学生信息（格式：姓名, 学号, 数学：分数, 语文：分数, 英语：分数, 编程：分数），按回车提交：\n"));

    }

    @Test
    public void should_return_empty_msg_print_output_when_input_error_order() throws Exception {
        //Given
        //When
        commandHandler.execute("-1");
        CmdParam param = commandHandler.execute("error order");
        //Then
        assertThat(param.getOutput(), is(""));

    }

    @Test
    public void should_return_add_stu_input_error_output_when_input_1_and_then_wrong_stu_info() throws Exception {
        //Given
        commandHandler.execute("-1");
        commandHandler.execute("1");
        //When

        CmdParam param  = commandHandler.execute("anything wrong format input");
        //Then
        assertThat(param.getOutput(), is("请按正确的格式输入（格式：姓名, 学号, 学科：成绩,...），按回车提交：\n"));
    }

    @Test
    public void should_return_main_menu_output_when_input_1_and_then_correct_stu_info() throws Exception {
        //Given
        commandHandler.execute("-1");
        commandHandler.execute("1");
        when(this.inputTransformer.formatStudentInfo(any())).thenReturn(new StudentInfo("122"));
        ;
        //When
        CmdParam param  = commandHandler.execute("Jason，122，数学：100，语文：99，英语：92，编程：100");
        //Then
        assertThat(param.getOutput(), is("\n***********\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3):\n" +
                "***********\n"));
        verify(this.mockStudentGradeService, times(1)).addStudent(any());
    }

    @Test
    public void should_return_main_menus_print_output_when_input_0() throws Exception {
        //Given
        //When
        CmdParam param  = commandHandler.execute("-1");
        //Then
        assertThat(param.getOutput(), is("\n***********\n" +
                "1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3):\n" +
                "***********\n"));
    }

    @Test
    public void should_return_gen_score_sheet_print_output_when_input_2() throws Exception {
        //Given
        commandHandler.execute("-1");
        //When
        CmdParam param  = commandHandler.execute("2");
        //Then
        assertThat(param.getOutput(), is("请输入要打印的学生的学号（格式：学号, 学号,...），按回车提交：\n"));
    }

    @Test
    public void should_return_input_num_format_error_output_when_input_2_then_wrong_input() throws Exception {
        //Given
        commandHandler.execute("-1");
        commandHandler.execute("2");
        when(this.inputTransformer.formatStudentNos(any())).thenReturn(anyListOf(StudentInfo.class));
        //When
        CmdParam param  = commandHandler.execute("anything is wrong format");
        //Then
        assertThat(param.getOutput(), is("请按正确的格式输入要打印的学生的学号（格式：学号, 学号,...），按回车提交：\n"));
    }

    @Test
    public void should_return_input_num_format_error_output_when_input_2_then_correct_input() throws Exception {
        //Given
        String printReportText = "成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分 \n" +
                "========================\n" +
                "张三|75|95|80|82|83|332\n" +
                "李四|85|80|70|81|79|316\n" +
                "========================\n" +
                "全班总分：648\n" +
                "全班总平均分：324";
        commandHandler.execute("-1");
        commandHandler.execute("2");
        when(this.inputTransformer.formatReportText(any())).thenReturn(printReportText);
        when(this.inputTransformer.formatStudentNos(any())).thenReturn(Arrays.asList(
                new StudentInfo("122"), new StudentInfo("223")));
        //When
        CmdParam param  = commandHandler.execute("112, 223");
        //Then
        verify(this.inputTransformer, times(1)).formatStudentNos(any());
        assertThat(param.getOutput(), containsString(printReportText));
        ;
    }
}
