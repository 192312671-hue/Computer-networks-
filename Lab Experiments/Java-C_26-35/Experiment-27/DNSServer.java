import java.net.*;
import java.util.*;

public class DNSServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);

        Map<String, String> dnsTable = new HashMap<>();
        dnsTable.put("google.com", "142.250.183.14");
        dnsTable.put("youtube.com", "142.250.72.206");
        dnsTable.put("example.com", "93.184.216.34");
        dnsTable.put("github.com", "140.82.112.4");

        byte[] buffer = new byte[1024];

        System.out.println("DNS Server started...");
        System.out.println("Waiting for DNS requests...");

        while (true) {
            DatagramPacket request =
                    new DatagramPacket(buffer, buffer.length);

            socket.receive(request);

            String domain = new String(
                    request.getData(), 0, request.getLength()).trim();

            System.out.println("DNS Query received: " + domain);

            String ip = dnsTable.getOrDefault(
                    domain, "Domain does not exist");

            byte[] responseData = ip.getBytes();

            DatagramPacket response = new DatagramPacket(
                    responseData,
                    responseData.length,
                    request.getAddress(),
                    request.getPort());

            socket.send(response);

            System.out.println("Response sent: " + ip);
        }
    }
}