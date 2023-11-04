package ChatProgram;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Port for server

            System.out.println("Server is running and waiting for clients...");

            Socket clientSocket1 = serverSocket.accept();
            System.out.println("Client 1 connected");

            Socket clientSocket2 = serverSocket.accept();
            System.out.println("Client 2 connected");

            // Create input and output streams for both clients
            DataInputStream in1 = new DataInputStream(clientSocket1.getInputStream());
            DataOutputStream out1 = new DataOutputStream(clientSocket1.getOutputStream());

            DataInputStream in2 = new DataInputStream(clientSocket2.getInputStream());
            DataOutputStream out2 = new DataOutputStream(clientSocket2.getOutputStream());

            while (true) {
                // Read messages from client 1 and send to client 2
                String message1 = in1.readUTF();
                System.out.println("Client 1: " + message1);
                out2.writeUTF("Client 1: " + message1);

                // Read messages from client 2 and send to client 1
                String message2 = in2.readUTF();
                System.out.println("Client 2: " + message2);
                out1.writeUTF("Client 2: " + message2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
