package com.datastructure.demo.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest2 {
    public static void main(String[] args) {
        List<Double> scores = new ArrayList<Double>();
        Collections.addAll(scores, 88.5, 100.0, 60.0, 99.0, 9.5, 99.6, 25.0);
        // 找出score>60的數據，asc
        scores.stream().filter(s -> s >= 60.0).sorted().forEach(System.out::println);

        List<Student> students = new ArrayList<>();
        Student s1 = new Student("James", 26, 175.5);
        Student s2 = new Student("James", 26, 175.5);
        Student s3 = new Student("Jack", 22, 167.6);
        Student s4 = new Student("John", 21, 164.6);
        Student s5 = new Student("Mary", 35, 164.6);
        Student s6 = new Student("Jack", 40, 144.9);
        Collections.addAll(students, s1, s2, s3, s4, s5, s6);
        // 30 >= age >= 23 student desc
        students.stream().filter(s -> s.getAge() >= 23 && s.getAge() <= 30)
                .sorted((o1, o2) -> o2.getAge() - o1.getAge())
                .forEach(System.out::println);
        System.out.println("===========================");
        // order 前3高學生
        students.stream().sorted((o1, o2) -> Double.compare(o2.getHeight(), o1.getHeight()))
                .limit(3)
                .forEach(System.out::println);
        System.out.println("===========================");
        // 身高倒數的前兩名
        students.stream().sorted((o1, o2)->Double.compare(o2.getHeight(), o1.getHeight()))
                .skip(students.size()-2).forEach(System.out::println);
        System.out.println("===========================");
        // 身高超過168的學生並去除重複名字
//        students.stream().filter(s -> s.getHeight() > 168).map(s -> s.getName()).forEach(System.out::println);
        students.stream().filter(s -> s.getHeight() > 168)
                .map(Student::getName).distinct()
                .forEach(System.out::println);
        // 若未使用map直接用Object去除重複，Object需override HashCode, equals
        students.stream().filter(s-> s.getHeight() > 150).distinct().forEach(System.out::println);

        System.out.println("===========================");
        Stream<String> st1 = Stream.of("Jace", "Alec");
        Stream<String> st2 = Stream.of("James", "Alex", "Kai");
        Stream<String> allSt = Stream.concat(st1, st2);
        allSt.forEach(System.out::println);
    }
}
