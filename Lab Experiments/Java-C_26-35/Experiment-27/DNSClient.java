import java.net.*;
import java.util.Scanner;

public class DNSClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter domain name: ");
        String domain = scanner.nextLine();

        byte[] data = domain.getBytes();

        InetAddress serverAddress =
                InetAddress.getByName("localhost");

        DatagramPacket request = new DatagramPacket(
                data,
                data.length,
                serverAddress,
                5000);

        socket.send(request);

        byte[] buffer = new byte[1024];

        DatagramPacket response =
                new DatagramPacket(buffer, buffer.length);

        socket.receive(response);

        String result = new String(
                response.getData(), 0, response.getLength());

        System.out.println("DNS Server Response: " + result);

        socket.close();
        scanner.close();
    }
}