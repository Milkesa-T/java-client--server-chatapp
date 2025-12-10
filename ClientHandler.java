import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class ClientHandler implements Runnable {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private ArrayList<ClientHandler> clients;
    private String clientName;

    public ClientHandler(Socket socket, ArrayList<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;

        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error initializing client handler");
        }
    }

    public String getClientName() {
        return clientName;
    }

    private String timestamp() {
        return new SimpleDateFormat("hh:mm a").format(new Date());
    }

    @Override
    public void run() {
        try {
            clientName = input.readUTF();

            System.out.println(clientName + " joined the chat.");
            ChatServer.broadcast("[" + timestamp() + "] " + clientName + " joined the chat.", this);

            while (true) {
                String msg = input.readUTF();

                // Handle /exit command
                if (msg.equalsIgnoreCase("/exit")) {
                    break;
                }

                // Handle /users command
                if (msg.equalsIgnoreCase("/users")) {
                    sendMessage(ChatServer.getUserList());
                    continue;
                }

                // Normal message
                String formatted = "[" + timestamp() + "] [" + clientName + "]: " + msg;
                System.out.println(formatted);

                ChatServer.broadcast(formatted, this);
            }

        } catch (IOException e) {
            // User closed client window
        }

        // User exits
        try {
            System.out.println(clientName + " left the chat.");
            ChatServer.broadcast("[" + timestamp() + "] " + clientName + " left the chat.", this);

            clients.remove(this);
            socket.close();
        } catch (IOException ignored) {}
    }

    public void sendMessage(String msg) {
        try {
            output.writeUTF(msg);
        } catch (IOException e) {
            System.out.println("Error sending to " + clientName);
        }
    }
}
