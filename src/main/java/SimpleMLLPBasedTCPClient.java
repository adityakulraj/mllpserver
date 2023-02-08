import java.net.*;
import java.io.*;

public class SimpleMLLPBasedTCPClient {

    private static final char END_OF_BLOCK = '\u001c';
    private static final char START_OF_BLOCK = '\u000b';
    private static final char CARRIAGE_RETURN = 13;

    public static void main(String[] args) throws IOException {

        // Create a socket to connect to server running locally on port 1080
        Socket socket = new Socket("localhost", 21);
        System.out.println("Connected to Server");

        StringBuffer testHL7MessageToTransmit = new StringBuffer();

        testHL7MessageToTransmit.append(START_OF_BLOCK)
                .append("MSH|^~\\&|AcmeHIS|StJohn|CATH|StJohn|20061019172719||ORM^O01|MSGID12349876|P|2.3")
                .append(CARRIAGE_RETURN)
                .append("PID|||20301||Durden^Tyler^^^Mr.||19700312|M|||88 Punchward Dr.^^Los Angeles^CA^11221^USA|||||||")
                .append(CARRIAGE_RETURN)
                .append("PV1||O|OP^^||||4652^Paulson^Robert|||OP|||||||||9|||||||||||||||||||||||||20061019172717|20061019172718")
                .append(CARRIAGE_RETURN)
                .append("ORC|NW|20061019172719")
                .append(CARRIAGE_RETURN)
                .append("OBR|1|20061019172719||76770^Ultrasound: retroperitoneal^C4|||12349876")
                .append(CARRIAGE_RETURN)
                .append(END_OF_BLOCK)
                .append(CARRIAGE_RETURN);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        // Send the MLLP-wrapped HL7 message to the server
        out.write(testHL7MessageToTransmit.toString().getBytes());

        byte[] byteBuffer = new byte[200];
        in.read(byteBuffer);

        System.out.println("Received from Server: " + new String(byteBuffer));

        // Close the socket and its streams
        socket.close();
    }
}