package com.thoughtworks.javapracise.entity;

public class StudentGradeItem {

    private String name;
    private String number;
    private int mathsScore;
    private int chineseScore;
    private int englishScore;
    private int programScore;

    public StudentGradeItem(String name, String number, int mathsScore, int chineseScore, int englishScore, int programScore) {

        this.name = name;
        this.number = number;
        this.mathsScore = mathsScore;
        this.chineseScore = chineseScore;
        this.englishScore = englishScore;
        this.programScore = programScore;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public int getMathsScore() {
        return mathsScore;
    }

    public int getChineseScore() {
        return chineseScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public int getProgramScore() {
        return programScore;
    }

    public int getTotalScore() {
        return this.chineseScore + this.getEnglishScore() + this.mathsScore + this.programScore;
    }

    public int getAvergeScore() {
        return getTotalScore() / 4;
    }
}
