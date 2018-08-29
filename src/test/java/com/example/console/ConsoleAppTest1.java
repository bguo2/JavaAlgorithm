package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleAppTest1 {

    @Test
    public void subsetTest() {
        int[] arr = {4,3,6};
        ArrayList<ArrayList<Integer>>  result = Subsets.subsets(arr);
        Iterator<ArrayList<Integer>> iterator = result.iterator();

        while(iterator.hasNext()){
            ArrayList<Integer> list = iterator.next();
        }
    }
}
