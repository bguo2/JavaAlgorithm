package com.example.console.graph;

import java.util.*;

//Component A depends on B, C, if A is installed, then B and C will be installed automatically
//If A is removed, then B and C will be removed. If A is installed, you cannot remove B or C
//If X depends on B, and if A installed, you don't need to install B.
//write a program to install and remove component automatically
//commands: depend A B C => define dependencies
//install A => install A
//remove B => remove B: B still needed because A depends on it
//list => list all components
public class Denpendency {
    class Node {
        String name;
        Set<Node> parents;
        Set<Node> children;
    }

    private final Map<String, Node> dependencyMap;
    private Set<String> installedComs;

    public Denpendency() {
        dependencyMap = new HashMap<>();
        installedComs = new LinkedHashSet<>();
    }

    //define dependencies
    public void depend(String a, String[] dependencies) {
        if(dependencies == null || dependencies.length == 0)
            return;
        Node node = dependencyMap.get(a);
        if(node == null) {
            node = new Node();
            node.name = a;
            node.parents = null;
            dependencyMap.put(a, node);
        }
        if(node.children == null)
            node.children = new LinkedHashSet<>();
        for(String depend: dependencies) {
            Node depNode = dependencyMap.get(depend);
            if(depNode == null) {
                depNode = new Node();
                depNode.name = depend;
                dependencyMap.put(depend, depNode);
            }
            if(depNode.parents == null)
                depNode.parents = new HashSet<>();
            depNode.parents.add(node);
            node.children.add(depNode);
        }
    }

    public boolean install(String a) {
        return install(a, false);
    }

    private boolean install(String a, boolean callback) {
        if(installedComs.contains(a)) {
            System.out.println(a + " is installed alreay");
            return true;
        }
        if(!callback)
            System.out.println("INSTALL " + a);
        else
            System.out.println("installing " + a);
        installedComs.add(a);
        //check dependency
        if(dependencyMap.containsKey(a)) {
            Node node = dependencyMap.get(a);
            if(node.children == null)
                return true;
            Iterator<Node> iterator = node.children.iterator();
            while (iterator.hasNext()) {
                Node next = iterator.next();
                if(next == null)
                    continue;
                install(next.name, true);
            }
        }

        return true;
    }

    public void list() {
        for(String a : installedComs) {
            System.out.println(a);
        }
    }

    public void remove(String a) {
        //not installed
        if(!installedComs.contains(a)) {
            System.out.println(a + " not installed or removed");
            return;
        }

        //anything depends on it and installed?
        Node node = dependencyMap.get(a);
        if(node != null && node.parents != null && node.parents.size() > 0) {
            System.out.println(a + " is still needed");
            return;
        }

        //a can be removed, all its dependencies can be removed
        removeCom(a);
    }

    private void removeCom(String a) {
        if(installedComs.isEmpty() || !installedComs.contains(a)) {
            System.out.println(a + " not installed");
            return;
        }

        System.out.println("Removing " + a);
        installedComs.remove(a);
        Node node = dependencyMap.get(a);
        if(node.children == null) {
            return;
        }

        for(Node child: node.children) {
            removeCom(child.name);
        }
    }

    public boolean cycleExists() {
        Set<String> visited = new HashSet<>();
        Set<String> rackSet = new HashSet<>();
        for(String key: dependencyMap.keySet()) {
            if(isCycle(key, visited, rackSet))
                return true;
        }

        return false;
    }

    private boolean isCycle(String v, Set<String> visited, Set<String> rackSet) {
        if(rackSet.contains(v))
            return true;
        if(visited.contains(v))
            return false;
        visited.add(v);
        rackSet.add(v);
        Node node = dependencyMap.get(v);
        if(node != null && node.children != null && node.children.size() > 0) {
            for(Node child: node.children) {
                if(isCycle(child.name, visited, rackSet))
                    return true;
            }
        }

        rackSet.remove(v);
        return false;
    }
}
