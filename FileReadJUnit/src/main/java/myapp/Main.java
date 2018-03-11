package myapp;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Main {
    //Прочитать файл, вывести статистику, сколько каких символов было найдено в файле
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Users\\Sveta\\IdeaProjects\\FileReadJUnit\\King - Misery.txt");
        System.out.println("file exists=" + f.exists());
        HashMap<Character, Integer> charsStat = getCharsStat(f);


    }


    public static HashMap<Character, Integer> getCharsStat(File f) throws IOException {
        if (f == null) throw new IllegalArgumentException("File is null. Recheck!");
        HashMap<Character, Integer> charStat = new HashMap<>();
        try (FileReader fr = new FileReader(f)){
            Integer value;
            while (fr.ready()) {
                char ch = (char) fr.read();
                if ((value=charStat.get(ch)) == null) {
                    charStat.put(ch, 1);
                    //  System.out.println("Впервые найден символ: "+ch);
                } else {
                    //  System.out.println("Символ "+ch+" уже добавлен "+charStat.get(ch));
                    // System.out.println("Увеличиваю счетчик кол-ва символа "+ch);
                    charStat.put(ch, ++value);
                }
            }
        }

        System.out.println("Result: ");
        for (Map.Entry<Character, Integer> entry : charStat.entrySet()) {
//            System.out.print((int)entry.getKey()+"\t ");
            System.out.println(entry);
        }
        return charStat;


    }
}
