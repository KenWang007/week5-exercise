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
        if(input.trim() == MAIN_MENU_DISPLAY_COMMAND){
            return MAIN_MENU_STATUS;
        }
        else if(input.trim() == ADD_STUDENT_DISPLAY_COMMAND){
            return ADD_STUDENT_STATUS;
        }
        else  if(input.trim() == PRINT_REPORT_DISPLAY_COMMAND){
            return PRINT_REPORT_STATUS;
        }else{
            return this.currentStatue;
        }
    }

    public boolean isTheSameStatus(String nextStatus) {
        return nextStatus.equals(this.currentStatue);

    }
}
