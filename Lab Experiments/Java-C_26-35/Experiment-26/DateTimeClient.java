import java.io.*;
import java.net.*;

public class DateTimeClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            String clientIP = InetAddress.getLocalHost().getHostAddress();

            out.println(clientIP);

            String dateTime = in.readLine();

            System.out.println("Connected to server.");
            System.out.println("Client IP Address: " + clientIP);
            System.out.println("Date and Time received from server: " + dateTime);

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}