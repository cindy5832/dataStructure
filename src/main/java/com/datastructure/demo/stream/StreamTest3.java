package com.datastructure.demo.stream;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest3 {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student("James", 26, 175.5);
        Student s2 = new Student("James", 26, 175.5);
        Student s3 = new Student("Jack", 22, 167.6);
        Student s4 = new Student("John", 21, 180.5);
        Student s5 = new Student("Mary", 35, 164.6);
        Student s6 = new Student("Jack", 40, 144.9);
        Collections.addAll(students, s1, s2, s3, s4, s5, s6);

        // 計算身高超出168人數
        long count = students.stream().filter(s -> s.getHeight() > 168).count();
        System.out.println(count);

        // 身高最高的對象 輸出
        Student student = students.stream().max((o1, o2) -> Double.compare(o1.getHeight(), o2.getHeight())).get();
        System.out.println(student);

        // 身高最矮的對象 輸出
        Student student1 = students.stream().min((o1, o2) -> Double.compare(o1.getHeight(), o2.getHeight())).get();
        System.out.println(student1);

        // 找出身高>170 存放帶新的集合中返回
        // 同一個Stream只能收集一次
        List<Student> studentList = students.stream().filter(a -> a.getHeight() > 170).collect(Collectors.toList());
        System.out.println(studentList);

        Set<Student> set = students.stream()
                .filter(a -> a.getHeight() > 170)
                .collect(Collectors.toSet());
        System.out.println(set);

        // 名字為key，身高為value
        Map<String, Double> map = students.stream()
                .filter(a -> a.getHeight() > 170)
                .distinct() // 避免重複key
                .collect(Collectors.toMap(a -> a.getName(), a -> a.getHeight()));
        System.out.println(map);

        Map<String, Double> map1 = students.stream()
                .filter(a -> a.getHeight() > 170)
                .distinct() // 避免重複key
                .collect(Collectors.toMap(Student::getName, Student::getHeight));
        System.out.println(map);

        Object[] arr = students.stream().filter(a -> a.getHeight() > 170).toArray();
        Student[] arr1 = students.stream().filter(a -> a.getHeight() > 170).toArray(o-> new Student[o]);
        Student[] arr2 = students.stream().filter(a -> a.getHeight() > 170).toArray(Student[]::new);
        System.out.println(Arrays.toString(arr));

    }
}
