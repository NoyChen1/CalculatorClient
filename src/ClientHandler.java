import java.io.*;
import java.net.*;

public class ClientHandler {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader stdIn = null;

        try {
            // Connect to the server on localhost and port 12345
            socket = new Socket("localhost", 12345);
            System.out.println("Connected to the server.");

            // Create output stream for communication with the server
            out = new PrintWriter(socket.getOutputStream(), true);
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                if ("=".equalsIgnoreCase(userInput)) {
                    break;
                }
            }

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (stdIn != null) stdIn.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
