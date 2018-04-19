package com.thoughtworks.javapracise.command;

public class CmdMessageConstants {
    public static final String MAIN_MENU_MSG = "\n***********\n" +
            "1. 添加学生\n" +
            "2. 生成成绩单\n" +
            "3. 退出\n" +
            "请输入你的选择（1～3):\n" +
            "***********\n";
    public static final String ADD_STUDENT_INFO_MSG = "请输入学生信息（格式：姓名, 学号, 数学：分数, 语文：分数, 英语：分数, 编程：分数），按回车提交：\n";
    public static final String PRINT_REPORT_MSG = "请输入要打印的学生的学号（格式：学号, 学号,...），按回车提交：\n";
    public static final String ADD_STUDENT_ERROR_MSG = "请按正确的格式输入（格式：姓名, 学号, 学科：成绩,...），按回车提交：\n";
    public static final String STUDENG_ADD_ERROR_MSG = "请按正确的格式输入要打印的学生的学号（格式：学号, 学号,...），按回车提交：\n";


}
