package ru.dmitriyK.servlets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String filename = "C:\\Users\\Dmitriy\\Desktop\\file.txt";
        Scanner scanner;
        List<MyData> myDataList = new ArrayList<>();

        try {
            scanner = new Scanner(new File(filename));

            while (scanner.hasNext()) {
                int i = 0;
                String line = scanner.nextLine();
                Scanner scanner1 = new Scanner(line).useDelimiter(", ");
                String[] temp = new String[3];

                while (scanner1.hasNext()) {
                    String variable = scanner1.next();
                    temp[i++] = variable;
                }

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Date date;
                try {
                    date = format.parse(temp[2]);
                    myDataList.add(new MyData(temp[0], Integer.parseInt(temp[1]), date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        Collections.sort(myDataList);
        System.out.println(myDataList);

        try {
            FileWriter writer = new FileWriter(filename, true);

            long memoryCount = Runtime.getRuntime().freeMemory();

            if (memoryCount > 1000000) {
                for (MyData aMyDataList : myDataList) {
                    writer.write("\r\n" + aMyDataList.getSerialNumber() + ", " +
                            aMyDataList.getNumber() + ", " +
                            aMyDataList.getDate()
                    );
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}