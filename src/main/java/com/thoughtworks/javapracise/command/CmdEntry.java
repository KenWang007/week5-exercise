package com.thoughtworks.javapracise.command;

import com.thoughtworks.javapracise.entity.Gradereport;
import com.thoughtworks.javapracise.entity.StudentInfo;
import com.thoughtworks.javapracise.service.StudentGradeService;
import com.thoughtworks.javapracise.transform.CmdIOTransformer;

import java.util.List;

import static com.thoughtworks.javapracise.command.CmdMessageConstants.*;
import static com.thoughtworks.javapracise.command.CmdStatusManager.*;

public class CmdEntry {

    private final CmdIOTransformer inputTransformer;
    private StudentGradeService studentGradeService;
    private final CmdStatusManager cmdStatusManager;

    public CmdEntry(StudentGradeService studentGradeService, CmdIOTransformer inputTransformer) {
        this.studentGradeService = studentGradeService;
        this.cmdStatusManager = new CmdStatusManager();
        this.inputTransformer = inputTransformer;
    }

    public CmdParam execute(String input) {

        CmdParam param = new CmdParam();
        String nextStatus = this.cmdStatusManager.getNextStatus(input);
        switch (nextStatus) {
            case MAIN_MENU_STATUS:
                if (cmdStatusManager.isTheSameStatus(nextStatus)) {
                    CmdParam cmdParam = new CmdParam();
                    cmdParam.setOutput("");
                    cmdParam.setStatus(MAIN_MENU_STATUS);
                    param = cmdParam;
                } else {
                    CmdParam cmdParam = new CmdParam();
                    cmdParam.setOutput(MAIN_MENU_MSG);
                    cmdParam.setStatus(MAIN_MENU_STATUS);
                    param = cmdParam;
                }
                break;
            case ADD_STUDENT_STATUS:
                if (this.cmdStatusManager.isTheSameStatus(nextStatus)) {

                    StudentInfo studentInfo = this.inputTransformer.formatStudentInfo(input);
                    if (studentInfo == null) {
                        CmdParam cmdParam = new CmdParam();
                        cmdParam.setOutput(ADD_STUDENT_ERROR_MSG);
                        cmdParam.setStatus(nextStatus);
                        param  = cmdParam;
                    } else {
                        studentGradeService.addStudent(studentInfo);
                        System.out.println("学生"+studentInfo.getName()+"的成绩被添加");
                        CmdParam cmdParam = new CmdParam();
                        cmdParam.setOutput(MAIN_MENU_MSG);
                        cmdParam.setStatus(nextStatus);
                        param  = cmdParam;
                    }
                } else {
                    CmdParam cmdParam = new CmdParam();
                    cmdParam.setOutput(ADD_STUDENT_INFO_MSG);
                    cmdParam.setStatus(nextStatus);
                    param = cmdParam;
                }
                break;
            case PRINT_REPORT_STATUS:
                if (this.cmdStatusManager.isTheSameStatus(nextStatus)) {
                    List<StudentInfo> studentInfos = this.inputTransformer.formatStudentNos(input);
                    if (studentInfos.isEmpty()) {
                        CmdParam cmdParam = new CmdParam();
                        cmdParam.setOutput(STUDENG_ADD_ERROR_MSG);
                        cmdParam.setStatus(nextStatus);
                        param = cmdParam;
                    } else {
                        Gradereport gradereport = studentGradeService.generateReport(studentInfos);
                        String displayMsg = this.inputTransformer.formatReportText(gradereport) + MAIN_MENU_MSG;
                        CmdParam cmdParam = new CmdParam();
                        cmdParam.setOutput(displayMsg);
                        cmdParam.setStatus(MAIN_MENU_STATUS);
                        param = cmdParam;
                    }
                } else {
                    CmdParam cmdParam = new CmdParam();
                    cmdParam.setOutput(PRINT_REPORT_MSG);
                    cmdParam.setStatus(nextStatus);
                    param  = cmdParam;
                }
                break;
        }

        this.cmdStatusManager.setCurrentStatue(param.getStatus());
        return param;
    }

}

