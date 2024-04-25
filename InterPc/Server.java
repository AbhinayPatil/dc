import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000; // Port on which the server will listen
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server started. Listening on port " + port);

            
                Socket clientSocket = serverSocket.accept(); // Accept incoming client connections
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create input and output streams for communication
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // ask client a que
                System.out.println("Getting Client info........ ");
                out.println("Pls Enter Your Name: ");
                out.flush();

                // Read the client's message
                String message = in.readLine();
                System.out.println("Client name: " + message);

                // Respond with an acknowledgment
                out.println("Details received");

                // Close the connection
                clientSocket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
