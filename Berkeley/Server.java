import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server started. Listening on port 8888...");
            
            while (true) {
                // Accept client connections
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                
              //InputStream objects to recieve from Server
                InputStream istream = clientSocket.getInputStream();
                //OutputStream object to write to Server
                OutputStream ostream = clientSocket.getOutputStream();
                
                //Reading input from KeyBoard
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                //Reading receieved message from Server        
                BufferedReader in = new BufferedReader(new InputStreamReader(istream));

                //PrintWriter object to send the data to the outputstream 
                PrintWriter out = new PrintWriter(ostream, true);
                
                // Read the client's request
                String request = in.readLine();
                
                // Process the client's request
                if (request.equals("SYNC_REQUEST")) {
                    // Perform clock synchronization and adjust the clock time
                    int adjustedTime = performClockSynchronization();
                    
                    // Send the adjusted clock time back to the client
                    out.println(adjustedTime);
                }
                
                // Close the client socket
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Perform clock synchronization and return the adjusted clock time
    private static int performClockSynchronization() {
        // Array of clocks representing each node
        int[] clocks = { 10, 12, 8, 15, 11 };
        				
        				
        
        // Get the average time of all clocks
        int averageTime = calculateAverageTime(clocks);
        System.out.println("Average Time: " + averageTime);
        
        // Adjust the clocks based on the average time
        adjustClocks(clocks, averageTime);
        
        // Assuming the server is one of the nodes, return its adjusted clock time
        return clocks[0];
    }
    
    // Calculate the average time of all clocks
    private static int calculateAverageTime(int[] clocks) {
        int sum = 0;
        for (int clock : clocks) {
            sum += clock;
        }
        return sum / clocks.length;
    }
    
    // Adjust the clocks based on the average time
    private static void adjustClocks(int[] clocks, int averageTime) {
        for (int i = 0; i < clocks.length; i++) {
            clocks[i] = clocks[i] - averageTime;
        }
    }
}
