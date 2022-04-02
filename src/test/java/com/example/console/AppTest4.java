package com.example.console;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest4 {

    class ListNode {
        ListNode next;
        int value;
    }

    private ListNode left;

    public boolean helper(ListNode right) {

        if(right == null)
            return true;
        boolean ret = helper(right.next);
        if(!ret)
            return  false;
        ret = (left.value == right.value);
        left = left.next;
        return ret;
    }

    @Test
    public void linkedListTest() {
        ListNode head = new ListNode();
        head.value = 1;
        left = head;
        boolean ret = helper(head);
        assertTrue( "", ret);

        ListNode node1 = new ListNode();
        node1.value = 2;
        head.next = node1;
        ListNode node2 = new ListNode();
        node1.next = node2;
        node2.value=1;

        left = head;

        ret = helper(head);
    }

    @Test
    public void CountStarsTest() {
        String s = "*|**|*|**";
        List<Integer> startIndex = Arrays.asList(1, 1);
        List<Integer> endIndex = Arrays.asList(1, 6);

        List<Integer> result = CountStars.countStarts(s, startIndex , endIndex);
        assertTrue("", result != null && result.get(0) == 0 && result.get(1) == 2);

        endIndex = Arrays.asList(1, 8);
        result = CountStars.countStarts(s, startIndex , endIndex);
        assertTrue("", result != null && result.get(0) == 0 && result.get(1) == 3);

        endIndex = Arrays.asList(1, 20);
        result = CountStars.countStarts(s, startIndex , endIndex);
        assertTrue("", result != null && result.get(0) == 0 && result.get(1) == 3);

        s = "*|||**";
        endIndex = Arrays.asList(1, 6);
        result = CountStars.countStarts(s, startIndex , endIndex);
        assertTrue("", result != null && result.get(0) == 0 && result.get(1) == 0);
    }

    @Test
    public void LargestRectangleHistogramTest() {
        int[] height = {2,1,5,6,2,3};
        int area = LargestRectangleHistogram.largestRectangle(height);
        assertTrue("", area == 10 );
    }
}
