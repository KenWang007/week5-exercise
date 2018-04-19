package com.thoughtworks.javapracise.transform;

import com.thoughtworks.javapracise.entity.Gradereport;
import com.thoughtworks.javapracise.entity.StudentInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CmdIOTransformer {

    private static final String STU_REGREX = "([^, ]+), (\\w+), 数学：(\\d+), 语文：(\\d+), 英语：(\\d+), 编程：(\\d+)";
    private static final String STU_NUM_REGREX = "^(\\d+[\\s]*+[,]+[\\s]*)*(\\d+)$";
    private static final String reportTemplate = "成绩单\n" +
            "姓名|数学|语文|英语|编程|平均分|总分 \n" +
            "========================\n" +
            "%1$s" +
            "========================\n" +
            "全班总分：%2$s\n" +
            "全班总平均分：%3$s";

    public StudentInfo formatStudentInfo(String input) {
        StudentInfo stu = null;
        Matcher matcher = Pattern.compile(STU_REGREX).matcher(input);
        if (matcher.matches()) {
            stu = new StudentInfo(matcher.group(1),
                    matcher.group(2),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)),
                    Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6)));
        }
        return stu;
    }


    public List<StudentInfo> formatStudentNos(String input) {

        List<StudentInfo> stus = new ArrayList<>();
        Matcher matcher = Pattern.compile(STU_NUM_REGREX).matcher(input);
        boolean isMatche = matcher.matches();

        if (!isMatche) {
            return stus;
        }
        stus = Arrays.asList(input.trim().split(",")).stream()
                .map(num -> num.trim())
                .distinct()
                .map(num -> new StudentInfo(num))
                .collect(Collectors.toList());

        return stus;
    }

    public String formatReportText(Gradereport gradereport) {
        ;

        String gradereportItemTemplate = "%1$s|%2$d|%3$d|%4$d|%5$d|%6$d|%7$d\n";

        String itemsText = gradereport.getStudentGradeItems().stream()
                .map(item -> String.format(gradereportItemTemplate, item.getName(),
                        item.getMathsScore(),
                        item.getChineseScore(),
                        item.getEnglishScore(),
                        item.getProgramScore(),
                        item.getAvergeScore(),
                        item.getTotalScore())).collect(Collectors.joining());


        return String.format(reportTemplate,
                itemsText,
                gradereport.getTotalScore(),
                gradereport.getAvergeScore());
    }
}
