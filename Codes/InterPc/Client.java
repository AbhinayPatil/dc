import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // The server's IP address (localhost for testing)
        int port = 5000; // Port to connect to
        
        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to server.");

            // Create input and output streams for communication
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Example Auth question from server
            System.out.println(in.readLine()); //display que from server

            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String ans = bf.readLine();

            // Send the message to the server
            out.println(ans);

            // Receive the response from the server
            String response = in.readLine();
            System.out.println("Received response from server:-  " + response);

            // Close the connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
