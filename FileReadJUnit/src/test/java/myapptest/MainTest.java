package myapptest;

import myapp.Main;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

/*
1. Не создавать просто файлы, а разместить их в одной тестовой директории. после всех тестов удалить директорию
2. Тестить, не заполняя файл отдельными символами. а сверятьс заранее "просчитанной" строкой (ее записыть в файл)
 */

public class MainTest {
    private Path testDocPath = null;


    @Test
    public void getCharsStatTestCyrillic() {

        //Path testDocPath = Paths.get(".\\TestCharsCyrillic.txt");
        testDocPath = Paths.get(".\\TestCharsCyrillic.txt");

        try {
            if (!Files.exists(testDocPath)) {
                Files.createFile(testDocPath);
            }
        } catch (IOException io) {
            Assert.fail("IO exception was thrown on file creation");
        }
        Integer vCalc = 5;
        Integer dCalc = 4;
        Integer lCalc = 8;

        try (BufferedWriter bw = Files.newBufferedWriter(testDocPath, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {

            // BufferedWriter bw = Files.newBufferedWriter(testDocPath, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
            for (int i = 0; i < vCalc; i++) {
                bw.write("в ");
                bw.newLine();
            }

            for (int i = 0; i < dCalc; i++) {
                bw.write("д ");
                bw.newLine();
            }


            for (int i = 0; i < lCalc; i++) {
                bw.write("л ");
                bw.newLine();
            }


        } catch (IOException io) {
            Assert.fail("IO exception was thrown on writing to file");
        }

        try {
            HashMap<Character, Integer> stat = Main.getCharsStat(testDocPath.toFile());
            Assert.assertEquals("Char 'в' stats", (Integer) vCalc, (Integer) stat.get('в'));
            Assert.assertEquals("Char 'д' stats", (Integer) dCalc, (Integer) stat.get('д'));
            Assert.assertEquals("Char 'л' stats", (Integer) lCalc, (Integer) stat.get('л'));

        } catch (IOException e) {
            Assert.fail("IO exception was thrown on getCharsStat Method");
        }


    }

    @Test
    public void getCharsStatTestLatin() {
        //Path testDocPath = Paths.get(".\\TestCharsLatin.txt");
        testDocPath = Paths.get(".\\TestCharsLatin.txt");

        try {
            if (!Files.exists(testDocPath)) {
                Files.createFile(testDocPath);
            }
        } catch (IOException io) {
            Assert.fail("IO exception was thrown on file creation");
        }
        Integer wCalc = 2;
        Integer jCalc = 11;
        Integer sCalc = 7;

        try (BufferedWriter bw = Files.newBufferedWriter(testDocPath, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {

            // BufferedWriter bw = Files.newBufferedWriter(testDocPath, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
            for (int i = 0; i < wCalc; i++) {
                bw.write("w ");
                bw.newLine();
            }

            for (int i = 0; i < jCalc; i++) {
                bw.write("j");
            }
            bw.newLine();


            for (int i = 0; i < sCalc; i++) {
                bw.write("slitov ");
            }
            bw.newLine();

        } catch (IOException io) {
            Assert.fail("IO exception was thrown on writing to file");
        }

        try {
            HashMap<Character, Integer> stat = Main.getCharsStat(testDocPath.toFile());
            Assert.assertEquals("Char 'w' stats", (Integer) wCalc, (Integer) stat.get('w'));
            Assert.assertEquals("Char 'j' stats", (Integer) jCalc, (Integer) stat.get('j'));
            Assert.assertEquals("Char 's' stats", (Integer) sCalc, (Integer) stat.get('s'));

        } catch (IOException e) {
            Assert.fail("IO exception was thrown on getCharsStat Method");
        }


    }

    @Test
    public void getCharsStatTestSigns() {
        // Path testDocPath = Paths.get(".\\TestCharsSigns.txt");
        testDocPath = Paths.get(".\\TestCharsSigns.txt");
        try {
            if (!Files.exists(testDocPath)) {
                Files.createFile(testDocPath);
            }
        } catch (IOException io) {
            Assert.fail("IO exception was thrown on file creation");
        }
        Integer starCalc = 5;
        Integer plusCalc = 2;
        Integer amperCalc = 8;

        try (BufferedWriter bw = Files.newBufferedWriter(testDocPath, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {

            // BufferedWriter bw = Files.newBufferedWriter(testDocPath, StandardCharsets.UTF_8, StandardOpenOption.WRITE);
            for (int i = 0; i < starCalc; i++) {
                bw.write("*smth else)))");
                bw.write("some other chars ");
            }
            bw.newLine();

            for (int i = 0; i < plusCalc; i++) {
                bw.write("+ ");
                bw.newLine();
            }


            for (int i = 0; i < amperCalc; i++) {
                bw.write("&!!!some other chars ");

            }
            bw.newLine();


        } catch (IOException io) {
            Assert.fail("IO exception was thrown on writing to file");
        }

        try {
            HashMap<Character, Integer> stat = Main.getCharsStat(testDocPath.toFile());
            Assert.assertEquals("Char '*' stats", (Integer) starCalc, (Integer) stat.get('*'));
            Assert.assertEquals("Char 'д' stats", (Integer) plusCalc, (Integer) stat.get('+'));
            Assert.assertEquals("Char 'л' stats", (Integer) amperCalc, (Integer) stat.get('&'));
            Assert.assertNull("Char calc than wasn't in file equals null", (Integer) stat.get('Q'));

        } catch (IOException e) {
            Assert.fail("IO exception was thrown on getCharsStat Method");
        }
    }


    @After
    public void deleteTestFiles() {
        if ((testDocPath != null) && (Files.exists(testDocPath))) {
            try {
                Files.delete(testDocPath);
            } catch (IOException io) {
                Assert.fail("Couldn't delete test doc");
            }
        }

    }
}



