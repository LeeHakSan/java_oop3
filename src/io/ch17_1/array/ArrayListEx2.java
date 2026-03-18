package io.ch17_1.array;

import java.util.ArrayList;

public class ArrayListEx2 {
    public static void main(String[] args) {
        // 정수, 실수, 불리언, 사용자 정의 객체를 담을 수 있는 ArrayList 각각 만들어 사용해보기
        // 사용 방법 스스로 익혀보기
        ArrayList<Integer> intList = new ArrayList<>();
        ArrayList<Float> floatList = new ArrayList<>();
        ArrayList<Double> doubleList = new ArrayList<>();
        ArrayList<Boolean> booleanList = new ArrayList<>();
        ArrayList<People> classList = new ArrayList<>();

        System.out.println("----------------------------");
        intList.add(1);
        intList.add(2);
        System.out.println(intList);
        System.out.println("----------------------------");

        floatList.add(1.5f);
        doubleList.add(1.5);
        System.out.println("----------------------------");


        for (int i = 0; i < 100; i++) {
            intList.add(i);
        }

        System.out.println(intList.getFirst());
        intList.addFirst(10);
        System.out.println(intList.getFirst());

        System.out.println(intList.size());
        System.out.println("----------------------------");

        booleanList.add(true);
        booleanList.addLast(false);


        classList.add(new People(20, "홍길동"));

    }


}

class People {
    private int age;
    private String name;

    public People(int age, String name) {
        this.age = age;
        this.name = name;
        System.out.println("객체 생성됨");
    }

}