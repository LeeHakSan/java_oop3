package io.ch17;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

/**
 * 보조기반 스트림에 대해 알아보자
 * 기반 스트림이 있어야 사용 가능
 */
public class ByteBufferedKeyboardConsole {
    public static void main(String[] args) {

        try (BufferedInputStream bis = new BufferedInputStream(System.in);
             BufferedOutputStream bos = new BufferedOutputStream(System.out)) {

            byte[] buffer = new byte[1024]; // 한 번에 1024 바이트씩 읽어라
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead); // 읽은 만큼만 쓴다

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
