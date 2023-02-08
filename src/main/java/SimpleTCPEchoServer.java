import java.net.*;
import java.io.*;

public class SimpleTCPEchoServer {

    private static final int BUFFER_SIZE = 200;

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1080);

        int receivedMessageSize;
        byte[] receivedByeBuffer = new byte[BUFFER_SIZE];

        while (true) {
            Socket clientSocket = serverSocket.accept();     // Get client connection

            System.out.println("Handling client at " +
                    clientSocket.getInetAddress().getHostAddress() + " through port " +
                    clientSocket.getPort());

            InputStream in = clientSocket.getInputStream();
            OutputStream out = clientSocket.getOutputStream();

            receivedMessageSize = in.read(receivedByeBuffer);
            out.write(receivedByeBuffer, 0, receivedMessageSize);

            clientSocket.close();  // Close the socket.  We are done serving this client
        }

    }
}