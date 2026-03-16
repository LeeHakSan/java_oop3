package io.ch14;

import java.io.FileInputStream;
import java.io.FileReader;

public class MyFileInputStream3 {
    public static void main(String[] args) {

        try (FileReader in = new FileReader("a_.txt")) {
            int readData;
            while ((readData = in.read()) != -1) {
                System.out.print((char)readData);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        try (FileReader in = new FileReader("img/FlapptBirdBackground.png")) {
//            int readData;
//            while ((readData = in.read()) != -1) {
//                System.out.print(readData + " ");
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    } // end of main
} // end of class
