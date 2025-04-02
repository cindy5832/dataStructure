package com.datastructure.demo.stream;

import java.util.*;
import java.util.stream.Stream;

public class StreamTest1 {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        Collections.addAll(names, "May", "Jensen", "Jarad", "Peter", "Derek");
        Stream<String> stream = names.stream();

        Set<String> set  = new HashSet<>();
        Collections.addAll(set, "May", "Jensen", "Jarad", "Peter", "Derek");
        Stream<String> stream2 = set.stream();
        stream2.filter(s -> s.contains("a")).forEach(System.out::println);

        Map<String, Double> map = new HashMap<>();
        map.put("aaaa", 172.3);
        map.put("ba",158.6);
        map.put("cfggeg", 344.4);
        map.put("dabrgre", 528.2);
        Set<String> keys = map.keySet();
        Stream<String> stream3 = keys.stream();

        Collection<Double> values = map.values();
        Stream<Double> vs = values.stream();

        Set<Map.Entry<String, Double>> entries = map.entrySet();
        Stream<Map.Entry<String, Double>> entries2 = entries.stream();
        entries2.filter(e -> e.getKey().contains("a")).forEach(e -> System.out.println(e.getValue() + " " + e.getKey()));

        String[] name2 = {"Clary", "Magnus", "Alec", "Izy"};
        Stream<String> s1 = Arrays.stream(name2);
        Stream<String> s2 = Stream.of(name2);
    }
}
