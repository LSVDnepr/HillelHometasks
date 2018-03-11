package junittask;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class SortArrayTest {


    @Test
    public void MySortArrayPositive() {
        for (int i = 0; i < 5; i++) {
            Double[] dArr = new Double[15];
            SortArray.fillArray(dArr);
            SortArray.mySortArray(dArr);
            Double[] dArr2 = Arrays.copyOf(dArr, dArr.length);
            SortArray.sortArray(dArr2);
            //Assert.assertTrue("Cheking if is sorted",Arrays.deepEquals(dArr,dArr2));
            Assert.assertArrayEquals("Cheking if is sorted", dArr, dArr2);
        }
    }

    @Test
    public void MySortArrayNegative() {
        for (int i = 0; i < 5; i++) {
            Double[] dArr = new Double[15];
            SortArray.fillArray(dArr);
            //  SortArray.mySortArray(dArr);
            Double[] dArr2 = Arrays.copyOf(dArr, dArr.length);
            SortArray.sortArray(dArr2);
            Assert.assertFalse("Cheking if not sorted", Arrays.deepEquals(dArr, dArr2));
            // Assert.assertArrayEquals("Cheking if is sorted", dArr, dArr2);
        }
    }

    @Test
    public void MySortArrayEmptyArray() {
            Double[] dArr = new Double[0];
            SortArray.fillArray(dArr);
            SortArray.mySortArray(dArr);
            Assert.assertEquals("Cheking if empty array returned", 0, dArr.length);
            }

    @Test
    public void MySortArrayNullArray() {
        Double[] nullArr = null;
        try {
            SortArray.mySortArray(nullArr);
        } catch (NullPointerException e) {
            Assert.fail("Method mySortArray has thrown NullPointerException");
        }

        //Assert.assertNull("Cheking if null array returned", nullArr);
    }

    @Test
    public void MySortArrayExceptionCheck() {
        Double[] arr =new Double[25];
        SortArray.fillArray(arr);
        arr[2]=null;
        try {
            SortArray.mySortArray(arr);
            Assert.fail("NullPointerException wasn't thrown");
        }catch (NullPointerException e){

        }

        //Assert.assertNull("Cheking if null array returned", nullArr);
    }





}
