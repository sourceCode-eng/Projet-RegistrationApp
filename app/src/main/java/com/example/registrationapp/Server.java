package com.example.registrationapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Server {
    public static String request(String request) throws IOException {
        // create socket connection to server
        Socket socket = new Socket("192.168.1.12", 1234);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // send request to server
        out.println(request);

        // read response from server
        String response = in.readLine();
        if (response.equals("failed")) throw new IOException();
        // close socket and streams
        socket.close();
        out.close();
        in.close();

        // return response to caller
        return response;
    }

}
