package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest6 {

    @Test
    public void trieTest() {
        Trie trie = new Trie();
        trie.insert("test");
        boolean found = trie.prefixSearch("test567");
        assertTrue("", found == false);

        found = trie.prefixSearch("tes");
        assertTrue("", found);
        found = trie.prefixSearch("test");
        assertTrue("", found);

        found = trie.search("tes");
        assertTrue("", found == false);
        found = trie.search("test");
        assertTrue("", found);
    }

    @Test
    public void wordSearchTest() {
        char[][] board = new char[][] {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };

        String[] words = new String[] {
                "oath","pea","eat","rain"
        };

        List<String> result = new WordSearch1().findWords(board, words);

        board = new char[][] {
                {'a', 'a'},
                {}
        };

        words = new String[] {"a", "aa"};
        result = new WordSearch1().findWords(board, words);
    }

    @Test
    public void flippingTest() {
        FlipGame.canWin("++++");
    }

    @Test
    public void listReverseTest() {
        ListImpl<Integer> list = new ListImpl<>();
        list.add(1);
        list.add(2);
        ListImpl.Node<Integer> end = list.add(3);
        list.add(4);

        ListImpl.Node<Integer> head = list.getHead();

        ListImpl.Node<Integer> newhead = list.reverse(head, end);

        list = new ListImpl<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        head = list.getHead();

        newhead = list.reverseKGroup(head, 2);
        assertTrue("", newhead.getData() == 2);
    }

    @Test
    public void meetingroomIITest() {
        MeetingRoomII test = new MeetingRoomII();

        int[][] intervals = new int[][] {
                {15, 16},
                {10, 15},
                {16, 25}
        };

        int count = test.minMeetingRooms(intervals);
        assertTrue("", count == 1);

        intervals = new int[][] {
                {1,5},
                {8,9},
                {8,9}
        };
        count = test.minMeetingRooms(intervals);
        assertTrue("", count == 2);

        intervals = new int[][] {
                {0, 30},
                {5,10},
                {15,20}
        };
        count = test.minMeetingRooms(intervals);
        assertTrue("", count == 2);
    }

    @Test
    public void palindromePartitionTest() {
        PalindromePartition test = new PalindromePartition();
        List<List<String>> result = test.getPartition("aab");

    }
}
