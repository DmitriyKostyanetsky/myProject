package aplana.HW3;

import java.io.*;
import java.util.*;

/**
 * Статистика текстового файла
 * @author Dmitriy Kostyanetsky
 * @version 1.0
 * @since 10.03.2019
 */
public class Solution {

    public static void main(String[] args) {
        String[] array = {"elder", "solution", "database", "commander", "white", "dry", "burn", "rum", "watch", "fury"};
        FileWriter writer;
        Random rnd = new Random();
        try {
            writer = new FileWriter(new File("HW3.doc"));
            BufferedWriter writer1 = new BufferedWriter(writer);
            writer1.write("");
            for (int i = 0; i < 10000; i++) {
                int index = rnd.nextInt(array.length);
                String randomStr = (array[index]);
                writer.write(randomStr);
                writer.write(" ");
            }

            writer.close();
            writer1.close();

            sortByAlph();
            wordsCount();
            sortByLength();
            averageLength(array);
            medianaValue(array);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортировка по алфавиту
     * @throws FileNotFoundException не найден файл
     */
    private static void sortByAlph() throws FileNotFoundException {
        List<String> text = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("HW3.doc"));
            while (scanner.hasNext()) {
                text.add(scanner.next());
            }
            Collections.sort(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter writer1 = new PrintWriter("HW3.doc");
        for (String str : text) {
            writer1.print(str + " ");
        }
        writer1.close();
    }

    /**
     * Подсчет количества раз, одинаково встречающихся слов
     */
    private static void wordsCount () {
        try {
            Scanner scanner = new Scanner(new File("HW3.doc"));
            Map<String, Integer> stats = new HashMap<>();
            Integer count;
            while (scanner.hasNext()) {
                String word = scanner.next();
                count = stats.get(word);
                if (count == null) {
                    count = 0;
                }
                stats.put(word, ++count);
            }
            System.out.println(stats);
            maxWord(stats);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Поиск слова, встречающегося максимальное кол-во раз
     * @param map коллекция со словами и количеством их повторений
     */
    private static void maxWord(Map<String, Integer> map) {
        Integer max = 0;
        String word = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > max) {
                max = value;
                word = key;
            }
        }
        System.out.println("Max word is " + word + " and found " + max + " time");
    }

    /**
     * Сортировка по длине слова
     * @throws FileNotFoundException не найден файл
     */
    private static void sortByLength() throws FileNotFoundException {
        List<String> text = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File("HW3.doc"));
            while (scanner.hasNext()) {
                text.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(text, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        PrintWriter writer1 = new PrintWriter("HW3(SortByLength).doc");
        for (String str : text) {
            writer1.print(str + " ");
        }
        writer1.close();
    }

    /**
     * Средняя длина слов
     * @param arr массив строк
     */
    private static void averageLength(String[] arr) {
        double result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i].length();
        }

        result = result / arr.length;
        System.out.println("Average length : " + result);
    }

    /**
     * Медианная длина слов
     * @param arr массив строк
     */
    private static void medianaValue(String[] arr) {
        Arrays.sort(arr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        int result;
        if (arr.length % 2 == 0) {
            int indexFirst = arr.length/ 2 - 1;
            int indexSecond = arr.length / 2;
            result = arr[indexFirst].length() + arr[indexSecond].length();
        } else {
            result = arr[arr.length / 2].length();
        }
        System.out.println("Mediana length : " + result);
    }
}