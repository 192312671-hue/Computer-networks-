import java.net.*;

public class DNSResolver {
    public static void main(String[] args) {
        try {
            String hostname = "google.com";

            InetAddress[] addresses = InetAddress.getAllByName(hostname);

            System.out.println("DNS Resolution");
            System.out.println("------------------------------");
            System.out.println("Host Name      : " + hostname);
            System.out.println("Address Length  : " + addresses.length);
            System.out.println("Address Type    : IPv4/IPv6");
            System.out.println();

            System.out.println("Resolved IP Addresses:");

            for (InetAddress address : addresses) {
                System.out.println("Host Name      : " + address.getHostName());
                System.out.println("IP Address     : " + address.getHostAddress());
                System.out.println("Address Type   : " +
                        (address instanceof Inet4Address ? "IPv4" : "IPv6"));
                System.out.println();
            }

        } catch (UnknownHostException e) {
            System.out.println("DNS resolution failed: " + e.getMessage());
        }
    }
}