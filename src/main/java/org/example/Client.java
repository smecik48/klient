package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        socket = new Socket("localhost", 5000);
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username: ");
        String login = scanner.nextLine();
        bufferedWriter.write(login);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        System.out.println("Write something to server: ");

        while(true){
            String msgToSend = scanner.nextLine();
            bufferedWriter.write(msgToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            System.out.println("Server: " + bufferedReader.readLine());

            if(msgToSend.equalsIgnoreCase("BYE")){
                break;
            }
        }
    }
}