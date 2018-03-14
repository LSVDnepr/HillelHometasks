package tasksolution;

import java.util.Arrays;
import java.util.Random;

public class MyApp {
    /*
- вывести на консоль буквы английского алфавита от 'A' до 'Z'.
- получить сумму чётных элементов целочисленного массива.
- написать функцию, которая принимает номер дня недели, а возвращает строку с названием дня недели.
- отсортировать целочисленный массив.

     */

    public static void main(String[] args) {
        //вывести на консоль буквы английского алфавита от 'A' до 'Z'.
        printLatinChars();

        //получить сумму чётных элементов челочисленного массива.
        int[] arr = fillArray(25);  //Заполняю массив:
        long sum = getEvenElSum(arr);//считаю сумму четных элементов массива
        System.out.println("Сумма четных элементов массива: " + sum);

        //написать функцию, которая принимает номер дня недели, а возвращает строку с названием дня недели.
        int dayNum=3;
        String dayName = getDayName(dayNum);
        System.out.println("Day name = " + dayName);

        //отсортировать целочисленный массив.
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        System.out.println("массив до сортировки: \n" + Arrays.toString(arr2));
        System.out.println("сортирую SelectionSort по возрастанию");
        selectionSort(arr2, true);
        System.out.println("массив после сортировки: \n" + Arrays.toString(arr2));

        int[] arr3 = Arrays.copyOf(arr, arr.length);
        System.out.println("массив до сортировки: \n" + Arrays.toString(arr3));
        System.out.println("сортирую SelectionSort по убыванию");
        selectionSort(arr3, false);
        System.out.println("массив после сортировки: \n" + Arrays.toString(arr3));

        int[] arr4 = Arrays.copyOf(arr, arr.length);
        System.out.println("массив до сортировки: \n" + Arrays.toString(arr4));
        System.out.println("сортирую BubbleSort по возрастанию");
        bubbleSort(arr4, true);
        System.out.println("массив после сортировки: \n" + Arrays.toString(arr4));

        int[] arr5 = Arrays.copyOf(arr, arr.length);
        System.out.println("массив до сортировки:\n" + Arrays.toString(arr5));
        System.out.println("сортирую BubbleSort по убыванию");
        bubbleSort(arr5, false);
        System.out.println("массив после сортировки:\n" + Arrays.toString(arr5));

        Integer[] array = fillIntegerArray(35);
        System.out.println("Integer Array до сортировки:\n" + Arrays.toString(array));
        sortArr(array, true);
        System.out.println("Integer Array после сортировки по возрастанию\n" + Arrays.toString(array));

        Integer[] array2 = fillIntegerArray(15);
        System.out.println("Integer Array2 до сортировки:\n" + Arrays.toString(array2));
        sortArr(array2, false);
        System.out.println("Integer Array2 после сортировки по убыванию\n" + Arrays.toString(array2));


    }


    // вывести на консоль буквы английского алфавита от 'A' до 'Z'.
    public static void printLatinChars() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            System.out.print(ch + " ");
        }
        System.out.println();

        //   или так:
      /*  for (char ch = 65; ch <= 90; ch++) {
            System.out.print(ch + " ");
        }
        System.out.println();*/

    }


    public static long getEvenElSum(int[] array) {// использую long, чтобы избежать переполнения при суммировании.
        // т.к. не знаю сколько элементов будет в массиве и насколько крупные числа это будут.
        if (array == null) throw new IllegalArgumentException("Array mustn't be null! Recheck!");
        long evenSum = 0;
        for (int el : array) {
            if (el % 2 != 0) continue;
            evenSum += el;
        }
        return evenSum;
    }


    public static String getDayName(int day) {
        String weekDay = null;
        switch (day) {
            case 1:
                weekDay = "Monday";
                break;
            case 2:
                weekDay = "Tuesday";
                break;
            case 3:
                weekDay = "Wednesday";
                break;
            case 4:
                weekDay = "Thursday";
                break;
            case 5:
                weekDay = "Friday";
                break;
            case 6:
                weekDay = "Saturday";
                break;
            case 7:
                weekDay = "Sunday";
                break;
            default:
                weekDay = "There is no such day number";
        }
        return weekDay;

    }


    public static int[] fillArray(int arrLength) {
        if (arrLength < 0) throw new IllegalArgumentException("Negative array size! Recheck!");
        int[] arr = new int[arrLength];
        Random rnd = new Random(255);
        for (int i = 0; i < arrLength; i++) {
            arr[i] = rnd.nextInt(1000);//позже можно убрать bound, но пока так удобнее проверять сортировку
        }
        return arr;

    }

    //Если надо работать с массивом Integer
    public static Integer[] fillIntegerArray(int arrLength) {
        if (arrLength < 0) throw new IllegalArgumentException("Negative array size! Recheck!");
        Integer[] arr = new Integer[arrLength];
        Random rnd = new Random(255);
        for (int i = 0; i < arrLength; i++) {
            arr[i] = rnd.nextInt(1000);//позже можно убрать bound, но пока так удобнее проверять сортировку
        }
        return arr;
    }

    //если надо сортировать массив Integer
    public static void sortArr(Integer[] arr, boolean isAscendingOrder) {
        if (arr == null) throw new IllegalArgumentException("Array to sort mustn't be null!");
        if (isAscendingOrder) {
            Arrays.sort(arr);
        } else {
            Arrays.sort(arr, (o1, o2) -> -(o1.compareTo(o2)));
        }
    }


    public static void selectionSort(int[] arr, boolean isAscendingOrder) {
        if (arr == null) throw new IllegalArgumentException("Array to sort mustn't be null!");
        if (isAscendingOrder) {
            for (int i = 0; i < arr.length; i++) {
                int minInd = i;
                for (int j = i; j < arr.length; j++) {
                    if (arr[j] >= arr[minInd]) continue;
                    minInd = j;
                }
                int temp = arr[minInd];
                arr[minInd] = arr[i];
                arr[i] = temp;
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                int maxInd = i;
                for (int j = i; j < arr.length; j++) {
                    if (arr[j] <= arr[maxInd]) continue;
                    maxInd = j;
                }
                int temp = arr[maxInd];
                arr[maxInd] = arr[i];
                arr[i] = temp;
            }
        }
    }


    public static void bubbleSort(int[] arr, boolean isAscendingOrder) {
        if (arr == null) throw new IllegalArgumentException("Array to sort mustn't be null!");
        if (isAscendingOrder) {
            for (int i = 0; i < arr.length; i++) {
                boolean changesMade = false;
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] <= arr[j + 1]) continue;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    changesMade = true;
                }
                if (!changesMade) break;
            }
        } else {
            for (int i = 0; i < arr.length; i++) {
                boolean changesMade = false;
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] >= arr[j + 1]) continue;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    changesMade = true;
                }
                if (!changesMade) break;
            }
        }

    }


}
