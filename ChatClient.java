import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to chat server!");

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            output.writeUTF(name);

            Thread readThread = new Thread(() -> {
                try {
                    while (true) {
                        String message = input.readUTF();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });

            readThread.start();

            while (true) {
                String msg = scanner.nextLine();
                output.writeUTF(msg);

                if (msg.equalsIgnoreCase("/exit")) {
                    System.out.println("You have left the chat.");
                    socket.close();
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Cannot connect to server.");
        }
    }
}
