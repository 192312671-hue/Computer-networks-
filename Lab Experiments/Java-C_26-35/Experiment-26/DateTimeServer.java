import java.io.*;
import java.net.*;
import java.time.LocalDateTime;

public class DateTimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected: " +
                    socket.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String clientIP = in.readLine();

            System.out.println("Client IP Address: " + clientIP);

            String dateTime = LocalDateTime.now().toString();
            out.println(dateTime);

            System.out.println("Date and Time sent to client: " + dateTime);

            in.close();
            out.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}