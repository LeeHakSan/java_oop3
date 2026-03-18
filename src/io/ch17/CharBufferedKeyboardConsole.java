package io.ch17;

import java.io.*;

public class CharBufferedKeyboardConsole {
    public static void main(String[] args) throws IOException {
        /**
         * stream 체인 구조
         *
         * [키보드]
         * System.in(input stream)
         * InputStreamReader(브릿지)
         * bufferedReader (버퍼 + readLine() 추가)
         *
         * [프로그램] --> 콘솔
         * BufferedWriter (버퍼 + readLine() 추가)
         * PrintWriter
         * System.out(PrintWriter) - 콘솔창에 출력
         *
         */

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        // BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));
        PrintWriter pw = new PrintWriter(System.out);
        BufferedWriter bw = new BufferedWriter(pw);

        // 콘솔창의 종료 명령 (ctrl + d)
        System.out.println("텍스트를 입력하세요 (종료 : 빈 줄 입력)");
        String line;
        while ((line = br.readLine()) != null) {
            bw.write("받은 값 출력 : " + line);
            bw.newLine(); // 운영체제에 맞는 줄 바꿈 자동 삽입
            // \n <-- 리눅스 mac
            // \r\n <-- 윈도우
            bw.flush(); // 버퍼에 남은 데이터 바로 출력
        }















    }
}
