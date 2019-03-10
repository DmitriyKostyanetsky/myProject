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
        String[] array = {"on", "dry", "fury", "white", "return", "account", "solution", "interface", "management", "autoantonym"};
        FileWriter writer;
        Random rnd = new Random();
        Map<String, Integer> map = null;
        List<String> sortList = null;
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

            // sort text by alphabet or length
            sortByAlph();
            sortList = sortByLength();

            // search max word
            map = wordsCount();

            // calculate by length or median
            averageLength(map);
            medianValue(sortList);

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
        Scanner scanner = new Scanner(new File("HW3.doc"));
        while (scanner.hasNext()) {
            text.add(scanner.next());
        }

        Collections.sort(text);
        PrintWriter writer = new PrintWriter("HW3(sortByAlph).doc");
        for (String str : text) {
            writer.print(str + " ");
        }
        writer.close();
    }

    /**
     * Сортировка по длине слова
     * @throws FileNotFoundException не найден файл
     */
    private static List<String> sortByLength() throws FileNotFoundException {
        List<String> text = new ArrayList<>();
        Scanner scanner = new Scanner(new File("HW3.doc"));
        while (scanner.hasNext()) {
            text.add(scanner.next());
        }

        Collections.sort(text, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        PrintWriter writer = new PrintWriter("HW3(SortByLength).doc");
        for (String str : text) {
            writer.print(str + " ");
        }
        writer.close();
        return text;
    }

    /**
     * Подсчет количества раз, одинаково встречающихся слов
     */
    private static Map<String, Integer> wordsCount () {
        Map<String, Integer> stats = null;
        try {
            Scanner scanner = new Scanner(new File("HW3.doc"));
            stats = new HashMap<>();
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
        return stats;
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
     * Средняя длина слов
     */
    private static void averageLength(Map<String, Integer> map) {
        double result = 0;

        if (map == null) {
            System.out.println("Map is null");
        } else {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                result += key.length() * value;
            }
            result = result / 10000;
            System.out.println("Average length : " + result);
        }
    }

    /**
     * Медианная длина слов
     */
    private static void medianValue(List<String> sortedList) {
        int result;
        if (sortedList == null) {
            System.out.println("List is null");
        } else {
            if (sortedList.size() % 2 == 0) {
                int indexFirst = 10000 / 2 - 1;
                int indexSecond = 10000 / 2;
                result = sortedList.get(indexFirst).length() + sortedList.get(indexSecond).length();
            } else {
                result = sortedList.get(sortedList.size() / 2).length();
            }
            System.out.println("Mediana length : " + result);
        }
    }
}