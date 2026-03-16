package io.ch15;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyFileOutputSystem {
    public static void main(String[] args) {

        String data = "Hello Java FileOutputSystem abc abc 반가워";
        String data2 = "한글도 잘 되는지 확인 ;',./\" <-- 특수문자";
        //FileOutputStream fos = new FileOutputStream("output.txt")
        // 파일이 없으면 새로 생성 있으면 덮어쓰기
        // FileOutputStream fos = new FileOutputStream("output.txt", true) --> Append 모드
        // 기존 파일에 내용이 있다면 true 뒤에 이어쓰기, false -> 덮어쓰기
        try (FileOutputStream fos = new FileOutputStream("output.txt", false)) {

            // 문자열은 FileOutputStream 으로 직접 사용 불가
            // 문자열을 -> byte 배열로 변환 해서 넣어줘야 한다.
            // 문자열을 바이트 배열로 변환해서 임시로 담아둠
//            byte[] dataBytes = data.getBytes();
            fos.write(data.getBytes());

            // fos.flush();
            System.out.println("파일 출력 완료 : output.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 참고 : output.txt를 에디터로 열면 텍스트가 보인다.
        // 에디터가 바이트 데이터를 문자로 해석하여 출력 하기 때문에
        // FileOutputStream으로 썼지만, 보여주는 방식은 에디터가 결정함

        try (FileOutputStream fos2 = new FileOutputStream("output2.txt", true)) {
            fos2.write(data2.getBytes());
            System.out.println("파일 출력 완료 : output2");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }// end of main
} // end of class
