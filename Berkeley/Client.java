import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 8888);
            
            //InputStream objects to recieve from Server
            InputStream istream = socket.getInputStream();
            //OutputStream object to write to Server
            OutputStream ostream = socket.getOutputStream();
            
            //Reading input from KeyBoard
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //Reading receieved message from Server        
            BufferedReader in = new BufferedReader(new InputStreamReader(istream));

            //PrintWriter object to send the data to the outputstream 
            PrintWriter out = new PrintWriter(ostream, true);
            
            
            // Send a request to the server for clock synchronization
            out.println("SYNC_REQUEST");
            
            // Receive the adjusted clock time from the server
            String adjustedTime = in.readLine();
            System.out.println("Adjusted Clock Time: " + adjustedTime);
            
            // Close the connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
