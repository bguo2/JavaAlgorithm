package com.example.console;

import java.util.*;

// a segment has a tag and its range e.g.
// A -> [1, 3]        A 1---------3
// B -> [2, 5.6]      B    2-------------5.6
// C -> [2.5, 9]      C       2.5----------------9
//
//output [1, 2] -> {A}
//[2, 2.5] -> {A, B}
//[2.5, 3] -> {A,B,C}
//[3, 5.6] -> {B,C}
//[5.6, 9] -> {C}
public class OutputSegment {
    private class Segment {
        String tag;
        double start, end;
        Segment(String tag, double start, double end) {
            this.tag = tag;
            this.start = start;
            this.end = end;
        }
    }

    public static class Response {
        Set<String> tags;
        double start, end;
        public Response(double start, double end, Set<String> tags) {
            this.start = start;
            this.end = end;
            this.tags = tags;
        }

        public Set<String> getTags() {
            return this.tags;
        }
    }

    public List<Response> getSegments(List<Segment> segments) {
        List<Response> result = new ArrayList<>();
        if(segments.isEmpty())
            return result;
        Collections.sort(segments, (s1, s2) -> {
            if(s1.start == s2.start) {
                if(s1.end == s2.end)
                    return 0;
                return s1.end > s2.end ? 1 : -1;
            }
            return s1.start > s2.start ? 1 : -1;
        });

        TreeMap<Double, Response> map = new TreeMap<>();
        for(Segment segment : segments) {
            if(map.isEmpty()) {
                map.put(segment.start, new Response(segment.start, segment.end, new HashSet<String>() {{
                    add(segment.tag);
                }}));
                continue;
            }
        }
        return result;
    }
}
