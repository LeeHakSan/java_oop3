package io.ch17;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageCopy {
    public static void main(String[] args) {
        String sourceFile = "abc.png";
        String destFile = "C:\\_work_java\\abc2.png";
        long start = System.currentTimeMillis();
        // abc.png --> abc2.png
        try (FileInputStream fis = new FileInputStream(sourceFile);
             FileOutputStream fos = new FileOutputStream(destFile)) {
            //                                           절대 경로 값을 입력해 다른 위치에 파일 저장 가능
//            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read()) != -1) {
                fos.write(bytesRead);
            }
            long end = System.currentTimeMillis();
            System.out.println(end - start);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
