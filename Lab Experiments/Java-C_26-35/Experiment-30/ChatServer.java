import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("TCP Chat Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected: " +
                    socket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String clientMessage = in.readLine();

                if (clientMessage == null ||
                        clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client ended the chat.");
                    break;
                }

                System.out.println("Client: " + clientMessage);

                System.out.print("Server: ");
                String serverMessage = scanner.nextLine();

                out.println(serverMessage);

                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Server ended the chat.");
                    break;
                }
            }

            socket.close();
            serverSocket.close();
            scanner.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}