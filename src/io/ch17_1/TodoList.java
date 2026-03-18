package io.ch17_1;

import io.ch17.BufferedFileReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===== To-do 리스트 =====");
        System.out.println("1. 할 일 추가");
        System.out.println("2. 전체 목록 보기");
        System.out.println("3. 완료 처리");
        System.out.println("4. 미완료 항목만 보기");
        System.out.println("5. 완료 취소하기");

        System.out.print("선택 : ");
        String choice = sc.nextLine();

        if (choice.equals("1")) {
            addTask(sc);
        } else if (choice.equals("2")) {
            searchTask();
        } else if (choice.equals("3")) {
            checkList(sc);
        } else if (choice.equals("4")) {
            failTodo();
        } else if (choice.equals("5")) {
            checkListBack(sc);
        }

        sc.close();

    } // end of main

    private static void checkList(Scanner sc) {

        ArrayList<String> tasks = new ArrayList<>();
        String line;
        int countLine = 1;
        String targetNumber;
        // 체크리스트 번호와 함께 출력
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            while ((line = br.readLine()) != null) {
                System.out.println(countLine + ". " + line); // notion에 적힌 요구 조건 수행
                tasks.add(line);
                countLine++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("완료한 항목의 번호를 입력하세요 : ");
        targetNumber = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt"))) {

            int target = (Integer.parseInt(targetNumber) - 1); // 입력 받은 값 정수 변경
            String targetString = tasks.get(target);
            tasks.set(target, "[v] " + targetString.substring(4)); // 체크 기능
            System.out.println("완료 처리됐습니다 : " + tasks.get(target));

            for (int i = 0; i < tasks.size(); i++) {

                if ((target) == i) {
                    bw.write(tasks.get(target));
                } else {
                    bw.write(tasks.get(i));
                }
                if (i != (tasks.size() - 1)) { // 마지막 줄에 \n을 넣지 않음
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void searchTask() {

        System.out.println("==== 전체 목록 ====");
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void addTask(Scanner sc) {
        System.out.println("추가할 일을 입력하세요 : ");
        String task = sc.nextLine();
        // "[] 할 일 내용" 형식으로 지정
        // [ ] 미완료 상태
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt", true))) {
            bw.write("[ ]" + task);
            bw.newLine();
            System.out.println("추가됐습니다 : " + task);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkListBack(Scanner sc) {

        ArrayList<String> tasks = new ArrayList<>();
        String line;
        int countLine = 1;
        String targetNumber;

        // 체크리스트 번호와 함께 출력
        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {

            while ((line = br.readLine()) != null) {
                tasks.add(line);
                if (line.contains("[v]")) {
                    System.out.println(countLine + ". " + line); // notion에 적힌 요구 조건 수행

                }
                countLine++;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.print("취소할 항목의 번호를 입력하세요 : ");
        targetNumber = sc.nextLine();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("todo.txt"))) {

            int target = (Integer.parseInt(targetNumber) - 1); // 입력 받은 값 정수 변경
            String targetString = tasks.get(target);
            tasks.set(target, "[ ] " + targetString.substring(4)); // 체크 기능

            System.out.println("취소 처리됐습니다 : " + tasks.get(target));

            for (int i = 0; i < tasks.size(); i++) {

                if ((target) == i) {
                    bw.write(tasks.get(target));
                } else {
                    bw.write(tasks.get(i));
                }
                if (i != (tasks.size() - 1)) { // 마지막 줄에 \n을 넣지 않음
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void failTodo() {

        try (BufferedReader br = new BufferedReader(new FileReader("todo.txt"))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("[ ]")) {
                    System.out.println(line);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


} // end of class
