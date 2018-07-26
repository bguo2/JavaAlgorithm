package com.example.console;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsoleApplicationTests {

    @Test
    public void undirectedGraphTest() {
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);

        boolean isCyclic = g.isCyclic();
        System.out.println("Is Cyclic: " + isCyclic);
        assertTrue(isCyclic);

        Graph g1 = new Graph(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(2,3);
        g1.addEdge(2, 4);
        isCyclic = g1.isCyclic();
        System.out.println("Is Cyclic: " + isCyclic);
        assertFalse(isCyclic);

        g1.addEdge(1,4);
        isCyclic = g1.isCyclic();
        System.out.println("Is Cyclic: " + isCyclic);
        assertTrue(isCyclic);
    }

    @Test
    public void directedGraphTest() {
        DirectedGraph g = new DirectedGraph(5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2,4);
        g.addEdge(3,4);
        boolean isCyclic = g.isCyclic();
        System.out.println("Directed graph is cyclic: "+ isCyclic);
        assertFalse(isCyclic);

        g.addEdge(4,1);
        isCyclic = g.isCyclic();
        System.out.println("Directed graph after add 4=>1 is cyclic: "+ isCyclic);
        assertFalse(isCyclic);

        g.addEdge(4,0);
        isCyclic = g.isCyclic();
        System.out.println("Directed graph after add 4=>0 is cyclic: "+ isCyclic);
        assertTrue(isCyclic);
    }
}
