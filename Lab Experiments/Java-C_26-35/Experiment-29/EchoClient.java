import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Connected to Echo Server.");

            String message;

            while (true) {
                System.out.print("Enter message: ");
                message = scanner.nextLine();

                out.println(message);

                String response = in.readLine();
                System.out.println("Echo from server: " + response);

                if (message.equalsIgnoreCase("exit"))
                    break;
            }

            socket.close();
            scanner.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}