package com.thoughtworks.javapracise.command;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class CmdStatusManager {

    public static final String MAIN_MENU_STATUS = "main_menu_status";
    public static final String ADD_STUDENT_STATUS = "add_student_status";
    public static final String PRINT_REPORT_STATUS = "print_report_status";
    public static final String MAIN_MENU_DISPLAY_COMMAND = "-1";
    public static final String ADD_STUDENT_DISPLAY_COMMAND = "1";
    public static final String PRINT_REPORT_DISPLAY_COMMAND = "2";
    private String currentStatue;

    public void setCurrentStatue(String currentStatue) {
        this.currentStatue = currentStatue;
    }


    public String getNextStatus(String input) {
        Map<String, String> commandToStatus = ImmutableMap.of(
                MAIN_MENU_DISPLAY_COMMAND, MAIN_MENU_STATUS,
                ADD_STUDENT_DISPLAY_COMMAND, ADD_STUDENT_STATUS,
                PRINT_REPORT_DISPLAY_COMMAND, PRINT_REPORT_STATUS);

        String newStatus = commandToStatus.get(input.trim());
        if (newStatus == null) {
            newStatus = this.currentStatue;
        }
        return newStatus;
    }

    public boolean isTheSameStatus(String nextStatus) {
        return nextStatus.equals(this.currentStatue);

    }
}
