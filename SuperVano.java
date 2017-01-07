import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class SuperVano {

    public static void main(String[] args) throws Exception {

        System.out.println("SuperVano v1.0");

        if(args.length < 1) {
            System.out.println("Usage: SuperVano <IP_1> <IP_2> ... <IP_n>");
            return;
        }

        System.out.println("Press CTRL+C to quit.");

        //Collect provided list of IPs
        List<InetAddress> members = new ArrayList<>();
        for(String ip: args) {
            members.add(InetAddress.getByName(ip));
        }

        //Prepare packet
        final int port = 16220;
        final String msg = "Yes! Me! My name is: \"abcde\" 16210 ";
        final byte[] data = msg.getBytes(Charset.forName("ASCII"));

        final DatagramPacket packet = new DatagramPacket(data, data.length);
        packet.setPort(port);

        //Send packets
        final DatagramSocket socket = new DatagramSocket();

        while(true) {
            System.out.print("Announcing server to");
            for(InetAddress address: members) {
                System.out.print(" " + address.getHostAddress());
                packet.setAddress(address);
                socket.send(packet);
            }
            System.out.println();

            Thread.sleep(2000);
        }
    }
}
