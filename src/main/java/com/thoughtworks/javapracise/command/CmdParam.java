package com.thoughtworks.javapracise.command;

public class CmdParam {
    private String output;
    private String status;

    public void setOutput(String msg) {
        this.output = msg;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getOutput() {
        return output;
    }
}
