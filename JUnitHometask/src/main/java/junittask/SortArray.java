package junittask;


import java.util.Arrays;
import java.util.Random;
/*
TESTING Д.З.
1. Написать обобщенный класс, реализующий метод сортировки массива
(1.1.реализовать сортировку самостоятельно
1.2.отдельно применить класс который умеет сортировать) (использовать базовую сортировку Java). Например, Arrays.sort()
 - Класс должен иметь внешнюю зависимость, которая и умеет сортировать.
 - Протестировать данный класс:
      проверить соответствие (вызвать сортировку и проверить, что массив действително отсортирован)
      проверить несоответствие (не вызвать сортировку и проверить, что массив действително НЕ отсортирован)
      проверить граничные условия
      проверить исключения (например, что мы выходим за границы массива)

 */


public class SortArray {
    public static void main(String[] args) {
        Integer[] arr = new Integer[25];
        fillArray(arr);
        Integer[] arr2=Arrays.copyOf(arr,arr.length);
        System.out.println("ИСХОДНЫЙ МАССИВ");
        System.out.println(Arrays.toString(arr));
      //  printArr(arr);
        System.out.println("МАССИВ ПОСЛЕ СОРТИРОВКИ ИЗ ARRAYS");
        sortArray(arr);
        System.out.println(Arrays.toString(arr));
       // printArr(arr);
        System.out.println("МАССИВ ПОСЛЕ СОРТИРОВКИ ИЗ MY SORT");
        mySortArray(arr2);
        System.out.println(Arrays.toString(arr));
        System.out.println("\n***\n");
        Double[] arr3 = new Double[25];
        fillArray(arr3);
        Double[] arr4=Arrays.copyOf(arr3,arr3.length);
        System.out.println("ИСХОДНЫЙ МАССИВ");
        System.out.println(Arrays.toString(arr3));
        //  printArr(arr);
        System.out.println("МАССИВ ПОСЛЕ СОРТИРОВКИ ИЗ ARRAYS");
        sortArray(arr3);
        System.out.println(Arrays.toString(arr3));
        // printArr(arr);
        System.out.println("МАССИВ ПОСЛЕ СОРТИРОВКИ ИЗ MY SORT");
        mySortArray(arr4);
        System.out.println(Arrays.toString(arr4));


    }


    public static void fillArray(Integer[] array) {
        if (array == null) throw new NullPointerException("Array is null!");
        if (array.length==0) return;
        Random rnd = new Random(342);
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(1000);
        }

    }

    public static void fillArray(Double[] array) {
        if (array == null) throw new NullPointerException("Array is null!");
        if (array.length==0) return;
        Random rnd = new Random(342);
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextDouble();
        }
    }

    public static <T extends Comparable<T>> void sortArray(T[] arr) {
        if (arr == null || arr.length == 0) return;
        Arrays.sort(arr);
    }


    public static <T extends Comparable<T>> void mySortArray(T[] arr) {
        if (arr == null || arr.length == 0) return;
        for (int i=0;i<arr.length;i++){
            int changesCalc=0;
            for (int j=0;j<arr.length-1-i;j++){
                //  System.out.println("сравниваю arr["+j+"]="+arr[j]+" и arr["+(j+1)+"]="+arr[j+1]);
                if (arr[j].compareTo(arr[j+1])<=0)continue;
                //     System.out.println("Меняю местами");
                T temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
                changesCalc++;
            }
             // System.out.println("changes made="+changesCalc);
           // System.out.println("Массив после " + i + "-й итерации");
           // System.out.println(Arrays.toString(arr));

            if (changesCalc==0) break;
        }
    }


}
