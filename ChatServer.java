import java.io.*;
import java.net.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class ChatServer {

    private static final int PORT = 5000;
    protected static ArrayList<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();

                // Display client IP & name
                InetAddress inet = socket.getInetAddress();
                System.out.println("Client Connected → " + inet.getHostName() + " (" + inet.getHostAddress() + ")");

                ClientHandler handler = new ClientHandler(socket, clients);
                clients.add(handler);

                Thread thread = new Thread(handler);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }
    // Return list of online users
    public static String getUserList() {
        StringBuilder sb = new StringBuilder("Online users: ");
        for (ClientHandler c : clients) {
            sb.append(c.getClientName()).append(", ");
        }
        return sb.toString();
    }
}
