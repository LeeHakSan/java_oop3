package client.ex;


import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5050)) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedOutputStream fileStream = new BufferedOutputStream(socket.getOutputStream());




            writer.println("서버 측 메세지를 쓸 때");

            String response = reader.readLine();
            System.out.println("서버에서 응답을 받을 때 : " + response);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
