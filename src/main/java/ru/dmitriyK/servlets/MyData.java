package ru.dmitriyK.servlets;


import java.util.Date;
import java.util.Scanner;

public class MyData implements Comparable {
    private String serialNumber;
    private int number;
    private Date date;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MyData(String serialNumber, int number, Date date) {
        this.serialNumber = serialNumber;
        this.number = number;
        this.date = date;
    }


    @Override
    public int compareTo(Object myData) {
        Scanner scanner = new Scanner(getSerialNumber()).useDelimiter(" ");
        Scanner scanner1 = new Scanner(((MyData) myData).getSerialNumber()).useDelimiter(" ");
        String[] strings = new String[2];
        String[] strings1 = new String[2];
        int i = 0;
        int j = 0;

        while (scanner.hasNext()) {
            strings[i++] = scanner.next();
        }

        while (scanner1.hasNext()) {
            strings1[j++] = scanner1.next();
        }

        return Integer.parseInt(strings[1]) - Integer.parseInt(strings1[1]);
    }
}