import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Connected to Chat Server.");

            while (true) {
                System.out.print("Client: ");
                String clientMessage = scanner.nextLine();

                out.println(clientMessage);

                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Chat terminated.");
                    break;
                }

                String serverMessage = in.readLine();

                if (serverMessage == null)
                    break;

                System.out.println("Server: " + serverMessage);

                if (serverMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Chat terminated.");
                    break;
                }
            }

            socket.close();
            scanner.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}