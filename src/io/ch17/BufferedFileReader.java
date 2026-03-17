package io.ch17;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedFileReader {
    public static void main(String[] args) {
        // FileReader는 기반 스트림을
        // BufferedReader(보조스트림)으로 감싼다
        long start = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            // readLine() : 한 줄 전체를 String으로 읽음
            // 반환값이 null이면 파일 끝
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));

    }
}
